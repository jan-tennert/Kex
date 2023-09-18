package io.github.jan.kex.di

import android.app.AlarmManager
import android.app.NotificationManager
import android.content.Context.NOTIFICATION_SERVICE
import io.github.jan.kex.vm.AuthenticationViewModel
import io.github.jan.kex.vm.ExamViewModel
import io.github.jan.kex.vm.SettingsViewModel
import io.github.jan.kex.vm.SubjectViewModel
import io.github.jan.kex.vm.TaskViewModel
import io.github.jan.kex.vm.UpdateViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        AuthenticationViewModel(get(), get())
    }
    viewModel { 
        ExamViewModel(get(), get(), get())
    }
    viewModel {
        SubjectViewModel(get(), get())
    }
    viewModel {
        TaskViewModel(get(), get(), get(), get())
    }
    viewModel {
        UpdateViewModel(get(), get())
    }
    viewModel {
        SettingsViewModel(get())
    }
    single {
        androidContext().getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }
    single {
        androidContext().getSystemService(AlarmManager::class.java)
    }
}