package io.github.jan.kex.data.remote

import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubjectData(
    val name: String,
    @SerialName("creator_id")
    val creatorId: String
)

@Serializable
data class Subject(
    val id: Long,
    val name: String
)

interface SubjectApi {

    suspend fun retrieveSubjects(): List<Subject>

    suspend fun createSubject(name: String): Subject

    suspend fun deleteSubject(subjectId: Long)

    suspend fun deleteSubjects(subjectIds: List<Long>)

    suspend fun updateSubject(id: Long, name: String)

}

internal class SubjectApiImpl(
    private val auth: Auth,
    postgrest: Postgrest
): SubjectApi {

    private val subjects = postgrest["subjects"]

    override suspend fun createSubject(name: String): Subject {
        val subject = SubjectData(name, auth.currentUserOrNull()!!.id)
        return subjects.insert(subject).decodeSingle()
    }

    override suspend fun deleteSubject(subjectId: Long) {
        subjects.delete {
            filter {
                Subject::id eq subjectId
            }
        }
    }

    override suspend fun deleteSubjects(subjectIds: List<Long>) {
        subjects.delete {
            filter {
                Subject::id isIn subjectIds
            }
        }
    }

    override suspend fun retrieveSubjects(): List<Subject> {
        return subjects.select().decodeList()
    }

    override suspend fun updateSubject(id: Long, name: String) {
        subjects.update(
            {
                Subject::name setTo name
            }
        ) {
            filter {
                Subject::id eq id
            }
        }
    }

}