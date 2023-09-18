package io.github.jan.kex.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.kex.R
import io.github.jan.kex.data.local.SubjectDataSource
import io.github.jan.kex.data.remote.Subject
import io.github.jan.kex.data.remote.SubjectApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SubjectViewModel(
    private val subjectApi: SubjectApi,
    private val subjectDataSource: SubjectDataSource
): ViewModel() {

    val subjects = subjectDataSource.getSubjectsAsFlow()
    val refreshing = MutableStateFlow(false)
    val errorMessage = MutableStateFlow<Int?>(null)

    fun refreshSubjects() {
        refreshing.value = true
        viewModelScope.launch {
            kotlin.runCatching {
                subjectApi.retrieveSubjects()
            }.onSuccess {
                subjectDataSource.insertSubject(it)
            }.onFailure {
                it.printStackTrace()
            }
            refreshing.value = false
        }
    }

    fun deleteSubject(id: Long) {
        viewModelScope.launch {
            kotlin.runCatching {
                subjectApi.deleteSubject(id)
            }.onSuccess {
                subjectDataSource.deleteSubject(id)
            }.onFailure {
                errorMessage.value = R.string.delete_subject_fail
                it.printStackTrace()
            }
        }
    }

    fun createSubject(name: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                subjectApi.createSubject(name)
            }.onSuccess {
                subjectDataSource.insertSubject(listOf(it))
            }.onFailure {
                errorMessage.value = R.string.create_subject_fail
                it.printStackTrace()
            }
        }
    }

    fun updateSubject(subject: Subject, name: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                subjectApi.updateSubject(subject.id, name)
            }.onSuccess {
                subjectDataSource.insertSubject(listOf(subject.copy(name = name)))
            }.onFailure {
                errorMessage.value = R.string.edit_subject_fail
                it.printStackTrace()
            }
        }
    }

    fun clearLocalEntries() {
        viewModelScope.launch {
            kotlin.runCatching {
                subjectDataSource.clear()
            }
        }
    }

}