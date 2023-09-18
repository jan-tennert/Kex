package io.github.jan.kex.notifications

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.work.impl.utils.ForceStopRunnable.BroadcastReceiver
import io.github.jan.kex.MainApplication
import io.github.jan.kex.R


class TaskBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val subjects = intent?.getStringArrayExtra("subjects")
        val epochDays = intent?.getIntExtra("epochDays", 0)
        if(subjects != null && epochDays != null) {
            showNotification(context, subjects, epochDays)
        }
    }

    private fun showNotification(context: Context, subjects: Array<String>, epochDays: Int) {
        val channelId = MainApplication.TASK_CHANNEL_ID // Use the same channel ID you created earlier
        val title = if(subjects.size > 1) context.applicationContext.getString(R.string.task_notification, subjects.size.toString()) else context.applicationContext.getString(R.string.task_notification_single)
        val text = context.applicationContext.getString(R.string.task_notification_detail, subjects.joinToString(", "))
        val icon = BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher)
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setLargeIcon(icon)
            .setSmallIcon(R.drawable.icon_notification)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(epochDays, notificationBuilder.build())
    }

}