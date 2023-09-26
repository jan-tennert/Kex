package io.github.jan.kex.notifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import io.github.jan.kex.MainActivity
import io.github.jan.kex.MainApplication
import io.github.jan.kex.R
import io.github.jan.kex.data.remote.Exam


class ExamBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val subject = intent?.getStringExtra("subject")
        val id = intent?.getIntExtra("id", 0)
        val type = intent?.getStringExtra("type")?.let { Exam.Type.valueOf(it) }
        if(subject != null && type != null && id != null) {
            showNotification(context, subject, type, id)
        }
    }

    private fun showNotification(context: Context, subject: String, type: Exam.Type, id: Int) {
        val channelId = MainApplication.EXAM_CHANNEL_ID // Use the same channel ID you created earlier
       // val title = context.applicationContext.getString(R.string.exam_notification_title, subject)
        val typeString = context.applicationContext.getString(type.nameId)
        val text = context.applicationContext.getString(R.string.exam_notification, subject, typeString)
        val icon = BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher)
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setLargeIcon(icon)
            .setSmallIcon(R.drawable.icon_notification)
       //     .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(
                PendingIntent.getActivity(
                context, 0,
                Intent(context, MainActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            ))

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(id, notificationBuilder.build())
    }

}