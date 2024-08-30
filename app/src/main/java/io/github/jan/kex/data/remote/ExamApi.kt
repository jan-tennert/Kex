package io.github.jan.kex.data.remote

import io.github.jan.kex.R
import io.github.jan.supabase.annotations.SupabaseInternal
import io.github.jan.supabase.functions.Functions
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.putJsonObject
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import kotlinx.datetime.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.*
import kotlin.time.Duration.Companion.days

@Serializable
data class ExamData(
    val subject: String,
    val date: String,
)

@Serializable
data class Exam(
    val id: Long = -1,
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

    companion object {
        val NOTIFICATION_DAY = 5.days
    }
}

interface ExamApi {

    suspend fun retrieveExamsFromSchool(username: String, password: String): List<ExamData>

    suspend fun retrieveExamData(): List<Exam>

    suspend fun updateExam(exam: Exam, subject: String, theme: String?, points: Long?, date: String?)

    suspend fun createExam(subject: String, date: String, theme: String, type: Exam.Type): Exam

    suspend fun createExams(exams: List<ExamData>): List<Exam>

    suspend fun deleteExam(examId: Long)

    suspend fun deleteExams(examIds: List<Long>)

}

internal class ExamApiImpl(
    private val functions: Functions,
    postgrest: Postgrest,
    private val auth: Auth
) : ExamApi {

    private val json = Json { encodeDefaults = false }
    private val exams = postgrest["exams_new"]
    private val getExams = functions.buildEdgeFunction("class-tests", headers = Headers.build {
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
    override suspend fun updateExam(exam: Exam, subject: String, theme: String?, points: Long?, date: String?) {
        exams.upsert(buildJsonObject {
            putJsonObject(
                Json.encodeToJsonElement(
                    exam.copy(
                        theme = theme,
                        subject = subject,
                        points = points,
                        date = date?.toCustomLocalDate() ?: exam.date
                    )
                ).jsonObject
            )
            put("creator_id", auth.currentUserOrNull()?.id)
        })
    }

    @OptIn(SupabaseInternal::class)
    override suspend fun createExam(subject: String, date: String, theme: String, type: Exam.Type): Exam {
        val exam = Exam(
            subject = subject,
            date = date.toCustomLocalDate(),
            theme = theme,
            type = type,
            custom = true
        )
        val newExam = exams.insert(buildJsonObject {
            putJsonObject(json.encodeToJsonElement(exam).jsonObject)
            put("creator_id", auth.currentUserOrNull()?.id)
        }) {
            select()
        }
        return newExam.decodeSingle()
    }

    @OptIn(SupabaseInternal::class)
    override suspend fun createExams(exams: List<ExamData>): List<Exam> {
        val examList = exams.map {
            it.toExam().copy(custom = true)
        }
        val result = this.exams.insert(buildJsonArray {
            examList.forEach {
                add(buildJsonObject {
                    putJsonObject(json.encodeToJsonElement(it).jsonObject)
                    put("creator_id", auth.currentUserOrNull()?.id)
                })
            }
        }) {
            select()
        }
        return result.decodeList()
    }

    override suspend fun deleteExam(examId: Long) {
        exams.delete {
            filter {
                Exam::id eq examId
            }
        }
    }

    override suspend fun deleteExams(examIds: List<Long>) {
        exams.delete {
            filter {
                Exam::id isIn examIds
            }
        }
    }

}

fun ExamData.toExam(): Exam {
    return Exam(
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