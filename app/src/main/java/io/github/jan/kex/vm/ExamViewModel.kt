package io.github.jan.kex.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.kex.R
import io.github.jan.kex.data.local.ExamDataSource
import io.github.jan.kex.data.local.SubjectSuggestionDataSource
import io.github.jan.kex.data.remote.*
import io.github.jan.kex.notifications.ExamNotificationManager
import io.github.jan.kex.toInstant
import io.github.jan.supabase.exceptions.BadRequestRestException
import io.github.jan.supabase.exceptions.HttpRequestException
import io.ktor.client.plugins.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime

class ExamViewModel(
    private val examApi: ExamApi,
    private val examDataSource: ExamDataSource,
    private val subjectSuggestionDataSource: SubjectSuggestionDataSource,
    private val examNotificationManager: ExamNotificationManager,

): ViewModel() {

    val exams: StateFlow<List<Exam>> = examDataSource.getExamsAsFlow().stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
    val isLoading = MutableStateFlow(false)
    val showPastExams = MutableStateFlow(false)
    val filteredExams: Flow<List<Exam>> = exams.combine(showPastExams) { exams, showPastExams ->
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        exams.filter { it.date > currentDate || showPastExams }.sortedBy { it.date }
    }
    val error = MutableStateFlow<Int?>(null)
    val subjectSuggestions: StateFlow<List<String>> = subjectSuggestionDataSource.getSuggestionsAsFlow().stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private suspend fun refreshExams(username: String?, password: String?) {
        kotlin.runCatching {
            val schoolExams = if(username != null && password != null) examApi.retrieveExamsFromSchool(username, password) else emptyList()
            val examData = examApi.retrieveExamData().map {
                if(!it.custom) {
                    if(schoolExams.none { exam -> exam.date == it.date.toCustomString() && exam.subject == it.subject }) it.copy(custom = true) else it
                } else it
            }
            val newExams = schoolExams.filter { examData.none { data -> it.date == data.date.toCustomString() && data.subject == it.subject } }.map(ExamData::toExam)
            examData + newExams
        }.onSuccess {
            examDataSource.insertExams(it, true)
            subjectSuggestionDataSource.insertAll(it.map(Exam::subject))
            scheduleOrUpdateNotifications(it)
        }.onFailure {
            when(it) {
                is BadRequestRestException -> error.value = R.string.invalid_school_credentials
            }
            it.printStackTrace()
        }
    }

    fun syncExams(username: String?, password: String?) {
        isLoading.value = true
        viewModelScope.launch {
            val tasks = examDataSource.getExams()
            val offlineCreated = tasks.filter { it.offlineCreated }
            offlineCreated.forEach { exam ->
                kotlin.runCatching {
                    examApi.createExam(exam.subject, exam.date.toCustomString(), exam.theme!!, exam.type)
                }.onSuccess {
                    examDataSource.updateOfflineCreated(it.id, false)
                }.onFailure {
                    //delete the exam if it was created offline and there is a RestException
                    it.printStackTrace()
                }
            }
            refreshExams(username, password)
            isLoading.value = false
        }
    }

    private suspend fun scheduleOrUpdateNotifications(exams: List<Exam>) {
        withContext(Dispatchers.Default) {
            exams.forEach { exam ->
                if(exam.date.toInstant() - Exam.NOTIFICATION_DAY > Clock.System.now()) {
                    examNotificationManager.scheduleNotifications(exam)
                }
            }
        }
    }

    fun updateExam(exam: Exam, subject: String, theme: String?, points: Long?, date: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            subjectSuggestionDataSource.insert(exam.subject)
            kotlin.runCatching {
                if(!exam.offlineCreated) {
                    examApi.updateExam(exam, subject, theme, points, date)
                }
            }.onSuccess {
                val newExam = exam.copy(subject = subject, theme = theme, points = points, date = date?.toCustomLocalDate() ?: exam.date)
                examDataSource.insertExams(listOf(newExam))
                examNotificationManager.scheduleNotifications(newExam)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun createExam(subject: String, date: String, theme: String, type: Exam.Type) {
        viewModelScope.launch(Dispatchers.IO) {
            subjectSuggestionDataSource.insert(subject)
            val currentExams = examApi.retrieveExamData()
            if(currentExams.any { it.subject == subject && it.date == date.toLocalDate() }) {
                //error.value = R.string.exam_already_exists
                return@launch
            }
            kotlin.runCatching {
                examApi.createExam(subject, date, theme, type)
            }.onSuccess {
                examDataSource.insertExams(listOf(it))
                examNotificationManager.scheduleNotifications(it)
            }.onFailure {
                when(it) {
                    is HttpRequestException, is HttpRequestTimeoutException -> {
                        val newExam = Exam(
                            subject = subject,
                            date = date.toCustomLocalDate(),
                            theme = theme,
                            type = type,
                            points = null,
                            offlineCreated = true,
                            custom = true
                        )
                        examDataSource.insertExams(listOf(newExam))
                        examNotificationManager.scheduleNotifications(newExam)
                    }
                }
                it.printStackTrace()
            }
        }
    }

    fun importExams(exams: List<ExamData>) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val currentExams = examApi.retrieveExamData()
                val noDuplicates = exams.filter { exam -> currentExams.none { it.date.toCustomString() == exam.date && it.subject == exam.subject } }
                examApi.createExams(noDuplicates)
            }.onSuccess {
                examDataSource.insertExams(it)
                scheduleOrUpdateNotifications(it)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun deleteExam(exam: Exam, custom: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                if(!exam.offlineCreated) {
                    examApi.deleteExam(exam.id)
                }
            }.onSuccess {
                if(custom) {
                    examDataSource.deleteExam(exam.id)
                    examNotificationManager.cancelNotification(exam.id)
                } else {
                    examDataSource.insertExams(listOf(exam.copy(theme = null, points = null)))
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun deleteExams(examIds: List<Long>) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                examApi.deleteExams(examIds)
            }.onSuccess {
                examNotificationManager.cancelNotifications(examIds)
                examDataSource.deleteExams(examIds)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun clearLocalEntries() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                examNotificationManager.cancelNotifications(exams.value.map(Exam::id))
                examDataSource.clear()
            }
        }
    }

    //importing:
}