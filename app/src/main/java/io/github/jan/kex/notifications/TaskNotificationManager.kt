package io.github.jan.kex.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import io.github.jan.kex.toDate
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration.Companion.days

interface TaskNotificationManager {


    fun scheduleNotifications(date: Instant, amount: Int, subjects: List<String>)

    fun cancelNotification(date: Instant)

}

internal class TaskNotificationManagerImpl(
    private val context: Context,
    private val alarmManager: AlarmManager,
): TaskNotificationManager {

    override fun scheduleNotifications(date: Instant, amount: Int, subjects: List<String>) {
        // Check if a notification is already scheduled for this task
        val id = date.toLocalDateTime(TimeZone.currentSystemDefault()).date.toEpochDays()
        val notificationIntent = Intent(context, TaskBroadcastReceiver::class.java)
        notificationIntent.putExtra("subjects", subjects.toTypedArray())
        notificationIntent.putExtra("epochDays", id)

        // Notification for this task doesn't exist, so schedule it
        val newPendingIntent = PendingIntent.getBroadcast(
            context,
            id,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Schedule the notification 1 day before the new due date
        val notificationTime = (date - 1.days).toEpochMilliseconds()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
            return
        }
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            notificationTime,
            newPendingIntent
        )
    }

    override fun cancelNotification(date: Instant) {
        val id = date.toDate().toEpochDays()
        val notificationIntent = Intent(context, TaskBroadcastReceiver::class.java)
        val newPendingIntent = PendingIntent.getBroadcast(
            context,
            id,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(newPendingIntent)
    }

}