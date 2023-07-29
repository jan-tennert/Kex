package io.github.jan.kex.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.kex.data.local.SubjectDataSource
import io.github.jan.kex.data.local.TaskDataSource
import io.github.jan.kex.data.remote.Subject
import io.github.jan.kex.data.remote.SubjectApi
import io.github.jan.kex.data.remote.Task
import io.github.jan.kex.data.remote.TaskApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant

class TaskViewModel(
    private val tasksApi: TaskApi,
    private val taskDataSource: TaskDataSource
): ViewModel() {

    val tasks = taskDataSource.getTasksAsFlow()
    val creatingTask = MutableStateFlow(false)

    fun refreshTasks() {
        viewModelScope.launch {
            kotlin.runCatching {
                tasksApi.retrieveTasks()
            }.onSuccess {
                taskDataSource.insertTask(it)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun deleteTask(id: Long) {
        viewModelScope.launch {
            taskDataSource.toggleLoading(id)
            kotlin.runCatching {
                tasksApi.deleteTask(id)
            }.onSuccess {
                taskDataSource.deleteTask(id)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun createTask(subjectId: Long, task: String, dueDate: Instant) {
        creatingTask.value = true
        viewModelScope.launch {
            kotlin.runCatching {
                tasksApi.createTask(subjectId, task, dueDate)
            }.onSuccess {
                taskDataSource.insertTask(listOf(it))
            }.onFailure {
                it.printStackTrace()
            }
            creatingTask.value = false
        }
    }

    fun updateTask(oldTask: Task, task: String, dueDate: Instant, doneDate: Instant?) {
        viewModelScope.launch {
            taskDataSource.toggleLoading(oldTask.id)
            kotlin.runCatching {
                tasksApi.updateSubject(oldTask.id, task, dueDate, doneDate)
            }.onSuccess {
                taskDataSource.insertTask(listOf(oldTask.copy(task = task, dueDate = dueDate, doneDate = doneDate, loading = false)))
            }.onFailure {
                taskDataSource.toggleLoading(oldTask.id)
                it.printStackTrace()
            }
        }
    }

    fun clearLocalEntries() {
        viewModelScope.launch {
            taskDataSource.clear()
        }
    }

}