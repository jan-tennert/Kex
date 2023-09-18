package io.github.jan.kex.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import io.github.jan.kex.data.remote.Exam
import io.github.jan.kex.toInstant

interface ExamNotificationManager {


    fun scheduleNotifications(exam: Exam)

    fun cancelNotifications(exams: List<String>)

    fun cancelNotification(id: String)

}

internal class ExamNotificationManagerImpl(
    private val context: Context,
    private val alarmManager: AlarmManager,
): ExamNotificationManager {

    override fun scheduleNotifications(exam: Exam) {
        // Check if a notification is already scheduled for this task
        val notificationIntent = Intent(context, ExamBroadcastReceiver::class.java)
        notificationIntent.putExtra("subject", exam.subject)
        notificationIntent.putExtra("type", exam.type.name)
        notificationIntent.putExtra("id", exam.id.hashCode())

        // Notification for this task doesn't exist, so schedule it
        val newPendingIntent = PendingIntent.getBroadcast(
            context,
            exam.id.hashCode(),
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Schedule the notification 5 days before the new due date
        val notificationTime = exam.date.toInstant() - Exam.NOTIFICATION_DAY
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
            return
        }
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            notificationTime.toEpochMilliseconds(),
            newPendingIntent
        )
    }

    override fun cancelNotification(id: String) {
        val notificationIntent = Intent(context, ExamBroadcastReceiver::class.java)
        val newPendingIntent = PendingIntent.getBroadcast(
            context,
            id.hashCode(),
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(newPendingIntent)
    }

    override fun cancelNotifications(exams: List<String>) {
        exams.forEach { cancelNotification(it) }
    }

}