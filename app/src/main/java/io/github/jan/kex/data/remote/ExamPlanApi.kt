package io.github.jan.kex.data.remote

import io.github.jan.supabase.storage.Storage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

typealias ExamPlanMap = Map<String, List<ExamPlanEntry>>

@Serializable
data class ExamPlanEntry(
    val subject: String,
    val teacher: String,
    @SerialName("abi")
    val isAbi: Boolean = false
)

data class ExamPlan(
    val examsByDate: ExamPlanMap
)

interface ExamPlanApi {

    suspend fun retrieveExamPlanKeys(): List<String>

    suspend fun retrieveExamPlan(key: String): ExamPlan

}

internal class ExamPlanApiImpl(
    private val storage: Storage
): ExamPlanApi {

    private val bucket = storage["exam-plan"]

    override suspend fun retrieveExamPlan(key: String): ExamPlan {
        val data = bucket.downloadAuthenticated("$key.json")
        val plan = Json.decodeFromString<ExamPlanMap>(data.decodeToString())
        return ExamPlan(plan)
    }

    override suspend fun retrieveExamPlanKeys(): List<String> {
        return bucket.list().filter { it.name.endsWith(".json") }.map {
            it.name.removeSuffix(".json")
        }
    }

}