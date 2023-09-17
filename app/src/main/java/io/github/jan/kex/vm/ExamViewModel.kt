package io.github.jan.kex.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.kex.R
import io.github.jan.kex.data.local.ExamDataSource
import io.github.jan.kex.data.local.SubjectSuggestionDataSource
import io.github.jan.kex.data.remote.Exam
import io.github.jan.kex.data.remote.ExamApi
import io.github.jan.kex.data.remote.ExamData
import io.github.jan.kex.data.remote.toCustomLocalDate
import io.github.jan.kex.data.remote.toCustomString
import io.github.jan.kex.data.remote.toExam
import io.github.jan.supabase.exceptions.BadRequestRestException
import io.github.jan.supabase.exceptions.HttpRequestException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class ExamViewModel(
    private val examApi: ExamApi,
    private val examDataSource: ExamDataSource,
    private val subjectSuggestionDataSource: SubjectSuggestionDataSource
): ViewModel() {

    val exams: Flow<List<Exam>> = examDataSource.getExamsAsFlow()
    val isLoading = MutableStateFlow(false)
    val showPastExams = MutableStateFlow(false)
    val filteredExams: Flow<List<Exam>> = exams.combine(showPastExams) { exams, showPastExams ->
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        exams.filter { it.date > currentDate || showPastExams }.sortedBy { it.date }
    }
    val error = MutableStateFlow<Int?>(null)
    val subjectSuggestions: StateFlow<List<String>> = subjectSuggestionDataSource.getSuggestionsAsFlow().stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private suspend fun refreshExams(username: String?, password: String?) {
        isLoading.value = true
        kotlin.runCatching {
            val schoolExams = if(username != null && password != null) examApi.retrieveExamsFromSchool(username, password) else emptyList()
            val examData = examApi.retrieveExamData().map {
                if(!it.custom) {
                    if(schoolExams.none { exam -> exam.id == it.id }) it.copy(custom = true) else it
                } else it
            }
            val newExams = schoolExams.filter { examData.none { data -> it.id == data.id } }.map(ExamData::toExam)
            examData + newExams
        }.onSuccess {
            examDataSource.insertExams(it)
            subjectSuggestionDataSource.insertAll(it.map(Exam::subject))
        }.onFailure {
            when(it) {
                is BadRequestRestException -> error.value = R.string.invalid_school_credentials
            }
            it.printStackTrace()
        }
        isLoading.value = false
    }

    fun syncExams(username: String?, password: String?) {
        viewModelScope.launch {
            val tasks = examDataSource.getExams()
            val offlineCreated = tasks.filter { it.offlineCreated }
            println(offlineCreated)
            offlineCreated.forEach { exam ->
                kotlin.runCatching {
                    examApi.createExam(exam.subject, exam.date.toCustomString(), exam.theme!!, exam.type)
                }.onSuccess {
                    examDataSource.updateOfflineCreated(it.id, false, it.id)
                }.onFailure {
                    //delete the exam if it was created offline and there is a RestException
                    it.printStackTrace()
                }
            }
            refreshExams(username, password)
        }
    }

    fun updateExam(exam: Exam, subject: String, theme: String?, points: Long?) {
        viewModelScope.launch(Dispatchers.IO) {
            subjectSuggestionDataSource.insert(exam.subject)
            kotlin.runCatching {
                examApi.updateExam(exam, subject, theme, points)
            }.onSuccess {
                examDataSource.insertExams(listOf(exam.copy(subject = subject, theme = theme, points = points)))
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun createExam(subject: String, date: String, theme: String, type: Exam.Type) {
        viewModelScope.launch(Dispatchers.IO) {
            subjectSuggestionDataSource.insert(subject)
            kotlin.runCatching {
                examApi.createExam(subject, date, theme, type)
            }.onSuccess {
                examDataSource.insertExams(listOf(it))
            }.onFailure {
                when(it) {
                    is HttpRequestException -> {
                        examDataSource.insertExams(listOf(Exam(
                            id = subject + date,
                            subject = subject,
                            date = date.toCustomLocalDate(),
                            theme = theme,
                            type = type,
                            points = null,
                            offlineCreated = true,
                            custom = true
                        )))
                    }
                }
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
                } else {
                    examDataSource.insertExams(listOf(exam.copy(theme = null, points = null)))
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun deleteExams(examIds: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                examApi.deleteExams(examIds)
            }.onSuccess {
                examDataSource.deleteExams(examIds)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun clearLocalEntries() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                examDataSource.clear()
            }
        }
    }

}