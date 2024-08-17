package io.github.jan.kex.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import io.github.jan.kex.data.LocalExam
import io.github.jan.kex.data.remote.Exam
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate

interface ExamDataSource {

    fun getExamsAsFlow(): Flow<List<Exam>>

    suspend fun getExams(): List<Exam>

    suspend fun insertExams(exams: List<Exam>, deleteRest: Boolean = false)

    suspend fun updateOfflineCreated(id: Long, offlineCreated: Boolean)

    suspend fun deleteExam(id: Long)

    suspend fun deleteExams(ids: List<Long>)

    suspend fun clear()

}

internal class ExamDataSourceImpl(
  appDatabase: AppDatabase
): ExamDataSource {

    private val queries = appDatabase.examQueries

    override fun getExamsAsFlow(): Flow<List<Exam>> {
        return queries.selectAll().asFlow().mapToList(Dispatchers.Default).map { it.map(LocalExam::toExam) }
    }

    override suspend fun getExams(): List<Exam> {
        return withContext(Dispatchers.IO) {
            queries.selectAll().executeAsList().map(LocalExam::toExam)
        }
    }

    override suspend fun insertExams(exams: List<Exam>, deleteRest: Boolean) {
        withContext(Dispatchers.IO) {
            val oldExams = getExams()
            val toDelete = if(deleteRest) oldExams.filter { oldExam -> exams.none { it.id == oldExam.id } && !oldExam.offlineCreated }.map { it.id } else emptyList()
            queries.transaction {
                exams.forEach { exam ->
                    queries.insert(
                        id = if(exam.id != -1L) exam.id else null,
                        subject = exam.subject,
                        date = exam.date.toString(),
                        theme = exam.theme,
                        type = exam.type.name,
                        custom = if (exam.custom) 1L else 0L,
                        points = exam.points,
                        offlineCreated = exam.offlineCreated
                    )
                }
                toDelete.forEach {
                    queries.delete(it)
                }
            }
        }
    }

    override suspend fun deleteExam(id: Long) {
        withContext(Dispatchers.IO) {
            queries.delete(id)
        }
    }

    override suspend fun deleteExams(ids: List<Long>) {
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

    override suspend fun updateOfflineCreated(id: Long, offlineCreated: Boolean) {
        withContext(Dispatchers.IO) {
            queries.updateOfflineCreated(offlineCreated, id)
        }
    }

}

fun LocalExam.toExam(): Exam {
    return Exam(
        id = id,
        subject = subject,
        date = LocalDate.parse(date),
        theme = theme,
        type = Exam.Type.valueOf(type),
        custom = custom == 1L,
        points = points,
        offlineCreated = offlineCreated
    )
}