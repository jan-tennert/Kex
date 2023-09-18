package io.github.jan.kex.di

import io.github.jan.kex.notifications.ExamNotificationManager
import io.github.jan.kex.notifications.ExamNotificationManagerImpl
import io.github.jan.kex.notifications.TaskNotificationManager
import io.github.jan.kex.notifications.TaskNotificationManagerImpl
import org.koin.dsl.module

val notificationModule = module {
    single<TaskNotificationManager> {
        TaskNotificationManagerImpl(get(), get())
    }
    single<ExamNotificationManager> {
        ExamNotificationManagerImpl(get(), get())
    }
}