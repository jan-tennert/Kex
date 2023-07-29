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

    suspend fun insertTask(exams: List<Task>)

    suspend fun deleteTask(id: Long)

    suspend fun deleteTasks(ids: List<Long>)

    suspend fun toggleLoading(id: Long)

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

    override suspend fun insertTask(exams: List<Task>) {
        withContext(Dispatchers.IO) {
            queries.transaction {
                exams.forEach { exam ->
                    queries.insert(
                        id = exam.id,
                        task = exam.task,
                        subjectId = exam.subjectId,
                        dueDate = exam.dueDate,
                        doneDate = exam.doneDate
                    )
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

}

fun LocalTask.toTask() = Task(
    id = id,
    task = task,
    subjectId = subjectId,
    dueDate = dueDate,
    doneDate = doneDate,
    loading = loading
)