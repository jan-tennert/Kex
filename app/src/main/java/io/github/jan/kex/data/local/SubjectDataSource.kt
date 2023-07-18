package io.github.jan.kex.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import io.github.jan.kex.data.LocalSubject
import io.github.jan.kex.data.remote.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

interface SubjectDataSource {

    fun getSubjectsAsFlow(): Flow<List<Subject>>

    suspend fun insertSubject(exams: List<Subject>)

    suspend fun deleteSubject(id: Long)

    suspend fun deleteSubjects(ids: List<Long>)

}

internal class SubjectDataSourceImpl(
    appDatabase: AppDatabase
): SubjectDataSource {

    private val queries = appDatabase.subjectQueries

    override suspend fun deleteSubject(id: Long) {
        withContext(Dispatchers.IO) {
            queries.delete(id)
        }
    }

    override suspend fun deleteSubjects(ids: List<Long>) {
        withContext(Dispatchers.IO) {
            queries.transaction {
                ids.forEach { id ->
                    queries.delete(id)
                }
            }
        }
    }

    override fun getSubjectsAsFlow(): Flow<List<Subject>> {
        return queries.selectAll().asFlow().mapToList(Dispatchers.Default).map { it.map(LocalSubject::toSubject) }
    }

    override suspend fun insertSubject(exams: List<Subject>) {
        withContext(Dispatchers.IO) {
            queries.transaction {
                exams.forEach { exam ->
                    queries.insert(
                        id = exam.id,
                        name = exam.name,
                    )
                }
            }
        }
    }

}

fun LocalSubject.toSubject() = Subject(
    id = id,
    name = name,
)