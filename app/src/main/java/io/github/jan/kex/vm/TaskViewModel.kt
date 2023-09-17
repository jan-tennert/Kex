package io.github.jan.kex.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.kex.data.local.TaskDataSource
import io.github.jan.kex.data.remote.Task
import io.github.jan.kex.data.remote.TaskApi
import io.github.jan.supabase.exceptions.HttpRequestException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant

class TaskViewModel(
    private val tasksApi: TaskApi,
    private val taskDataSource: TaskDataSource
): ViewModel() {

    val tasks = taskDataSource.getTasksAsFlow()
    val creatingTask = MutableStateFlow(false)
    val refreshing = MutableStateFlow(false)

    private suspend fun refreshTasks() {
        refreshing.value = true
        kotlin.runCatching {
            tasksApi.retrieveTasks()
        }.onSuccess {
            taskDataSource.insertTask(it)
        }.onFailure {
            it.printStackTrace()
        }
        refreshing.value = false
    }

    fun syncTasks() {
        viewModelScope.launch {
            val tasks = taskDataSource.getTasks()
            val offlineCreated = tasks.filter { it.offlineCreated }
            offlineCreated.forEach { task ->
                kotlin.runCatching {
                    tasksApi.createTask(task.subjectId, task.task, task.dueDate)
                }.onSuccess {
                    taskDataSource.updateOfflineCreated(task.id, false, it.id)
                }.onFailure {
                    it.printStackTrace()
                }
            }
            refreshTasks()
        }
    }

    fun deleteTask(id: Long, offlineCreated: Boolean) {
        viewModelScope.launch {
            taskDataSource.toggleLoading(id)
            kotlin.runCatching {
                if(!offlineCreated) {
                    tasksApi.deleteTask(id)
                }
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
                when(it) {
                    is HttpRequestException -> {
                        taskDataSource.insertTask(listOf(Task(
                            id = -1,
                            task = task,
                            dueDate = dueDate,
                            subjectId = subjectId,
                            loading = false,
                            offlineCreated = true
                        )))
                    }
                }
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