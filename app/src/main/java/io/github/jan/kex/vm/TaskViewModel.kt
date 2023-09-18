package io.github.jan.kex.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.kex.R
import io.github.jan.kex.data.local.SubjectDataSource
import io.github.jan.kex.data.local.TaskDataSource
import io.github.jan.kex.data.remote.Task
import io.github.jan.kex.data.remote.TaskApi
import io.github.jan.kex.notifications.TaskNotificationManager
import io.github.jan.kex.toDate
import io.github.jan.supabase.exceptions.HttpRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

class TaskViewModel(
    private val tasksApi: TaskApi,
    private val taskDataSource: TaskDataSource,
    private val subjectDataSource: SubjectDataSource,
    private val taskNotificationManager: TaskNotificationManager
): ViewModel() {

    val tasks = taskDataSource.getTasksAsFlow().stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
    private val creatingTask = MutableStateFlow(false)
    val refreshing = MutableStateFlow(false)
    val errorMessage = MutableStateFlow<Int?>(null)

    private suspend fun refreshTasks() {
        refreshing.value = true
        kotlin.runCatching {
            tasksApi.retrieveTasks()
        }.onSuccess {
            taskDataSource.insertTask(it)
            scheduleOrUpdateNotifications(it)
        }.onFailure {
            it.printStackTrace()
        }
        refreshing.value = false
    }

    private suspend fun scheduleOrUpdateNotifications(tasks: List<Task>) {
        withContext(Dispatchers.Default) {
            val subjects = subjectDataSource.getSubjects()
            tasks.filter { it.doneDate == null }.groupBy { it.dueDate }.forEach { (date, tasks) ->
                if((date - Task.NOTIFICATION_DAY) > Clock.System.now()) { // Only schedule notifications for tasks that are at least 1 day in the future
                    taskNotificationManager.scheduleNotifications(date, tasks.size, tasks.map { task ->
                        subjects.find { it.id == task.subjectId }?.name ?: ""
                    })
                }
            }
        }
    }

    private suspend fun potentiallyCancelNotification(task: Task) {
        withContext(Dispatchers.Default) {
            val amount = tasks.value.count { it.dueDate.toDate() == task.dueDate.toDate() && it.doneDate != null && it.id != task.id }
            if(amount == 0) {
                taskNotificationManager.cancelNotification(task.dueDate)
            }
        }
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
                potentiallyCancelNotification(tasks.value.first { it.id == id })
                taskDataSource.deleteTask(id)
                scheduleOrUpdateNotifications(tasks.value.filter { it.id != id })
            }.onFailure {
                taskDataSource.toggleLoading(id)
                errorMessage.value = R.string.delete_task_fail
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
                scheduleOrUpdateNotifications(tasks.value + it)
            }.onFailure {
                when(it) {
                    is HttpRequestException, is HttpRequestTimeoutException -> {
                        val newTask = Task(
                            id = -1,
                            task = task,
                            dueDate = dueDate,
                            subjectId = subjectId,
                            loading = false,
                            offlineCreated = true
                        )
                        taskDataSource.insertTask(listOf(newTask))
                        scheduleOrUpdateNotifications(tasks.value + newTask)
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
                if(!oldTask.offlineCreated) {
                    tasksApi.updateSubject(oldTask.id, task, dueDate, doneDate)
                }
            }.onSuccess {
                val newTask = oldTask.copy(task = task, dueDate = dueDate, doneDate = doneDate, loading = false)
                taskDataSource.insertTask(listOf(newTask))
                scheduleOrUpdateNotifications(tasks.value.filter { it.id != oldTask.id } + newTask)
            }.onFailure {
                taskDataSource.toggleLoading(oldTask.id)
                errorMessage.value = R.string.edit_task_fail
                it.printStackTrace()
            }
        }
    }

    fun clearLocalEntries() {
        viewModelScope.launch {
            taskNotificationManager.cancelNotifications(tasks.value.map(Task::dueDate))
            taskDataSource.clear()
        }
    }

}