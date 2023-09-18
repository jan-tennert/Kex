package io.github.jan.kex

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import io.github.jan.kex.di.appModule
import io.github.jan.kex.di.localModule
import io.github.jan.kex.di.notificationModule
import io.github.jan.kex.di.remoteModule
import io.github.jan.kex.di.supabaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(remoteModule, localModule, supabaseModule, appModule, notificationModule)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(TASK_CHANNEL_ID, "kex", importance).apply {
                description = "kex"
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val TASK_CHANNEL_ID = "task_channel_id"
    }

}