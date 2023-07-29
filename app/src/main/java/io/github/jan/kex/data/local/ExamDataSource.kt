package io.github.jan.kex.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import io.github.jan.kex.data.LocalExam
import io.github.jan.kex.data.remote.Exam
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.datetime.toLocalDate

interface ExamDataSource {

    fun getExamsAsFlow(): Flow<List<Exam>>

    suspend fun insertExams(exams: List<Exam>)

    suspend fun deleteExam(id: String)

    suspend fun deleteExams(ids: List<String>)

    suspend fun clear()

}

internal class ExamDataSourceImpl(
  appDatabase: AppDatabase
): ExamDataSource {

    private val queries = appDatabase.examQueries

    override fun getExamsAsFlow(): Flow<List<Exam>> {
        return queries.selectAll().asFlow().mapToList(Dispatchers.Default).map { it.map(LocalExam::toExam) }
    }

    override suspend fun insertExams(exams: List<Exam>) {
        withContext(Dispatchers.IO) {
            queries.transaction {
                exams.forEach { exam ->
                    queries.insert(
                        id = exam.id,
                        subject = exam.subject,
                        date = exam.date.toString(),
                        theme = exam.theme,
                        type = exam.type.name,
                        custom = if (exam.custom) 1L else 0L,
                        points = exam.points
                    )
                }
            }
        }
    }

    override suspend fun deleteExam(id: String) {
        withContext(Dispatchers.IO) {
            queries.delete(id)
        }
    }

    override suspend fun deleteExams(ids: List<String>) {
        withContext(Dispatchers.IO) {
            queries.transaction {
                ids.forEach { id ->
                    queries.delete(id)
                }
            }
        }
    }

    override suspend fun clear() {
        withContext(Dispatchers.IO) {
            queries.deleteAll()
        }
    }

}

fun LocalExam.toExam(): Exam {
    return Exam(
        id = id,
        subject = subject,
        date = date.toLocalDate(),
        theme = theme,
        type = Exam.Type.valueOf(type),
        custom = custom == 1L,
        points = points
    )
}