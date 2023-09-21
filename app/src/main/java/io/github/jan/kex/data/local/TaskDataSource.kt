package io.github.jan.kex.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import io.github.jan.kex.data.LocalTask
import io.github.jan.kex.data.remote.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

interface TaskDataSource {

    fun getTasksAsFlow(): Flow<List<Task>>

    suspend fun getTasks(): List<Task>

    suspend fun insertTask(tasks: List<Task>, deleteRest: Boolean = false)

    suspend fun deleteTask(id: Long)

    suspend fun deleteTasks(ids: List<Long>)

    suspend fun toggleLoading(id: Long)

    suspend fun updateOfflineCreated(id: Long, offlineCreated: Boolean, newId: Long)

    suspend fun clear()

}

internal class TaskDataSourceImpl(
    appDatabase: AppDatabase
): TaskDataSource {

    private val queries = appDatabase.taskQueries

    override suspend fun deleteTask(id: Long) {
        withContext(Dispatchers.IO) {
            queries.delete(id)
        }
    }

    override suspend fun deleteTasks(ids: List<Long>) {
        withContext(Dispatchers.IO) {
            queries.transaction {
                ids.forEach { id ->
                    queries.delete(id)
                }
            }
        }
    }

    override fun getTasksAsFlow(): Flow<List<Task>> {
        return queries.selectAll().asFlow().mapToList(Dispatchers.Default).map { it.map(LocalTask::toTask) }
    }

    override suspend fun getTasks(): List<Task> {
        return withContext(Dispatchers.IO) {
            queries.selectAll().executeAsList().map(LocalTask::toTask)
        }
    }

    override suspend fun insertTask(tasks: List<Task>, deleteRest: Boolean) {
        withContext(Dispatchers.IO) {
            val oldTasks = getTasks()
            val toDelete = if(deleteRest) oldTasks.filter { oldTask -> tasks.none { it.id == oldTask.id } }.map { it.id } else emptyList()
            queries.transaction {
                tasks.forEach { task ->
                    queries.insert(
                        id = if(task.id == -1L) null else task.id,
                        task = task.task,
                        subjectId = task.subjectId,
                        dueDate = task.dueDate,
                        doneDate = task.doneDate,
                        offlineCreated = task.offlineCreated,
                    )
                }
                toDelete.forEach {
                    queries.delete(it)
                }
            }
        }
    }

    override suspend fun toggleLoading(id: Long) {
        withContext(Dispatchers.IO) {
            queries.toggleLoading(id)
        }
    }

    override suspend fun clear() {
        withContext(Dispatchers.IO) {
            queries.deleteAll()
        }
    }

    override suspend fun updateOfflineCreated(id: Long, offlineCreated: Boolean, newId: Long) {
        withContext(Dispatchers.IO) {
            queries.updateOfflineCreated(offlineCreated, id, newId)
        }
    }

}

fun LocalTask.toTask() = Task(
    id = id,
    task = task,
    subjectId = subjectId,
    dueDate = dueDate,
    doneDate = doneDate,
    loading = loading,
    offlineCreated = offlineCreated,
)