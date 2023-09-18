package io.github.jan.kex.data.remote

import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.time.Duration.Companion.days

@Serializable
data class TaskData(
    val task: String,
    @SerialName("due_date")
    val dueDate: Instant,
    @SerialName("creator_id")
    val creatorId: String,
    @SerialName("subject_id")
    val subjectId: Long,
    @SerialName("done_date")
    val doneDate: Instant? = null
)

@Serializable
data class Task(
    val id: Long,
    val task: String,
    @SerialName("due_date")
    val dueDate: Instant,
    @SerialName("subject_id")
    val subjectId: Long,
    @SerialName("done_date")
    val doneDate: Instant? = null,
    @Transient
    val loading: Boolean = false,
    @Transient
    val offlineCreated: Boolean = false
) {

     companion object {
         val NOTIFICATION_DAY = 1.days
     }

}

interface TaskApi {

    suspend fun retrieveTasks(): List<Task>

    suspend fun createTask(subjectId: Long, task: String, dueDate: Instant): Task

    suspend fun deleteTask(subjectId: Long)

    suspend fun deleteTasks(subjectIds: List<Long>)

    suspend fun updateSubject(id: Long, task: String, dueDate: Instant, doneDate: Instant?)

}

internal class TaskApiImpl(
    private val goTrue: GoTrue,
    postgrest: Postgrest
): TaskApi {

    private val subjects = postgrest["tasks"]

    override suspend fun createTask(subjectId: Long, task: String, dueDate: Instant): Task {
        val subject = TaskData(task, dueDate, goTrue.currentUserOrNull()!!.id, subjectId)
        return subjects.insert(subject).decodeSingle()
    }

    override suspend fun deleteTask(subjectId: Long) {
        subjects.delete {
            Task::id eq subjectId
        }
    }

    override suspend fun deleteTasks(subjectIds: List<Long>) {
        subjects.delete {
            Task::id isIn subjectIds
        }
    }

    override suspend fun retrieveTasks(): List<Task> {
        return subjects.select().decodeList()
    }


    override suspend fun updateSubject(id: Long, task: String, dueDate: Instant, doneDate: Instant?) {
        subjects.update(
            {
                Task::task setTo task
                Task::dueDate setTo dueDate
                Task::doneDate setTo doneDate
            }
        ) {
            Task::id eq id
        }
    }

}