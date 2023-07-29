package io.github.jan.kex.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.kex.data.local.ExamDataSource
import io.github.jan.kex.data.remote.Exam
import io.github.jan.kex.data.remote.ExamApi
import io.github.jan.kex.data.remote.ExamData
import io.github.jan.kex.data.remote.toExam
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class ExamViewModel(
    private val examApi: ExamApi,
    private val examDataSource: ExamDataSource
): ViewModel() {

    val exams: Flow<List<Exam>> = examDataSource.getExamsAsFlow()
    val isLoading = MutableStateFlow(false)
    val showPastExams = MutableStateFlow(false)
    val filteredExams: Flow<List<Exam>> = exams.combine(showPastExams) { exams, showPastExams ->
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        exams.filter { it.date > currentDate || showPastExams }.sortedBy { it.date }
    }

    fun refreshExams(username: String, password: String) {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val schoolExams = examApi.retrieveExamsFromSchool(username, password)
                val examData = examApi.retrieveExamData().map {
                    if(!it.custom) {
                        if(schoolExams.none { exam -> exam.id == it.id }) it.copy(custom = true) else it
                    } else it
                }
                val newExams = schoolExams.filter { examData.none { data -> it.id == data.id } }.map(ExamData::toExam)
                examData + newExams
            }.onSuccess {
                examDataSource.insertExams(it)
            }.onFailure {
                it.printStackTrace()
            }
            isLoading.value = false
        }
    }

    fun updateExam(exam: Exam, subject: String, theme: String?, points: Long?) {
        viewModelScope.launch(Dispatchers.IO) {
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
            kotlin.runCatching {
                examApi.createExam(subject, date, theme, type)
            }.onSuccess {
                examDataSource.insertExams(listOf(it))
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun deleteExam(exam: Exam, custom: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                examApi.deleteExam(exam.id)
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