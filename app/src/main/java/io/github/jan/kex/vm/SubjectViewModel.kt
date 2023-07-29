package io.github.jan.kex.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.kex.data.local.SubjectDataSource
import io.github.jan.kex.data.remote.Subject
import io.github.jan.kex.data.remote.SubjectApi
import kotlinx.coroutines.launch

class SubjectViewModel(
    private val subjectApi: SubjectApi,
    private val subjectDataSource: SubjectDataSource
): ViewModel() {

    val subjects = subjectDataSource.getSubjectsAsFlow()

    fun refreshSubjects() {
        viewModelScope.launch {
            kotlin.runCatching {
                subjectApi.retrieveSubjects()
            }.onSuccess {
                subjectDataSource.insertSubject(it)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun deleteSubject(id: Long) {
        viewModelScope.launch {
            kotlin.runCatching {
                subjectApi.deleteSubject(id)
            }.onSuccess {
                subjectDataSource.deleteSubject(id)
            }.onFailure {
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