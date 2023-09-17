package io.github.jan.kex.data.remote

import io.github.jan.kex.R
import io.github.jan.supabase.annotations.SupabaseInternal
import io.github.jan.supabase.functions.Functions
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.putJsonObject
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.todayIn
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.put

@Serializable
data class ExamData(
    val subject: String,
    val date: String,
) {

    val id = subject + date

}

@Serializable
data class Exam(
    val id: String,
    val subject: String,
    val date: LocalDate,
    val theme: String?,
    val type: Type,
    val custom: Boolean,
    val points: Long? = null,
    @Transient
    val offlineCreated: Boolean = false
) {

    val daysUntil: Int
        get() = Clock.System.todayIn(TimeZone.currentSystemDefault()).daysUntil(date)

    enum class Type(val nameId: Int) {
        EXAM(R.string.exam), TEST(R.string.test), GFS(R.string.gfs)
    }
}

interface ExamApi {

    suspend fun retrieveExamsFromSchool(username: String, password: String): List<ExamData>

    suspend fun retrieveExamData(): List<Exam>

    suspend fun updateExam(exam: Exam, subject: String, theme: String?, points: Long?)

    suspend fun createExam(subject: String, date: String, theme: String, type: Exam.Type): Exam

    suspend fun deleteExam(examId: String)

    suspend fun deleteExams(examIds: List<String>)

}

internal class ExamApiImpl(
    private val functions: Functions,
    postgrest: Postgrest,
    private val gotrue: GoTrue
) : ExamApi {

    private val exams = postgrest["exams"]
    private val getExams = functions.buildEdgeFunction("class-tests", Headers.build {
        append(HttpHeaders.ContentType, "application/json")
    })

    override suspend fun retrieveExamsFromSchool(
        username: String,
        password: String
    ): List<ExamData> {
        return getExams {
            parameter("username", username)
            parameter("password", password)
        }.body()
    }

    override suspend fun retrieveExamData(): List<Exam> {
        return exams.select().decodeList()
    }

    @OptIn(SupabaseInternal::class)
    override suspend fun updateExam(exam: Exam, subject: String, theme: String?, points: Long?) {
        exams.insert(buildJsonObject {
            putJsonObject(
                Json.encodeToJsonElement(
                    exam.copy(
                        theme = theme,
                        subject = subject,
                        points = points
                    )
                ).jsonObject
            )
            put("creator_id", gotrue.currentUserOrNull()?.id)
        }, upsert = true)
    }

    @OptIn(SupabaseInternal::class)
    override suspend fun createExam(subject: String, date: String, theme: String, type: Exam.Type): Exam {
        val exam = Exam(
            id = subject + date,
            subject = subject,
            date = date.toCustomLocalDate(),
            theme = theme,
            type = type,
            custom = true
        )
        exams.insert(buildJsonObject {
            putJsonObject(Json.encodeToJsonElement(exam).jsonObject)
            put("creator_id", gotrue.currentUserOrNull()?.id)
        })
        return exam
    }

    override suspend fun deleteExam(examId: String) {
        exams.delete {
            Exam::id eq examId
        }
    }

    override suspend fun deleteExams(examIds: List<String>) {
        exams.delete {
            Exam::id isIn examIds
        }
    }

}

fun ExamData.toExam(): Exam {
    return Exam(
        id = subject + date,
        subject = subject,
        date = date.toCustomLocalDate(),
        theme = null,
        type = Exam.Type.EXAM,
        custom = false
    )
}

fun String.toCustomLocalDate(): LocalDate {
    val parts = split(".")
    return LocalDate(parts[2].toInt(), parts[1].toInt(), parts[0].toInt())
}

fun LocalDate.toCustomString(): String {
    return "$dayOfMonth.$monthNumber.$year"
}