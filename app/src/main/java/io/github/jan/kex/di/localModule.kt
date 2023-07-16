package io.github.jan.kex.di

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import io.github.jan.kex.data.local.AppDatabase
import io.github.jan.kex.data.local.ExamDataSource
import io.github.jan.kex.data.local.ExamDataSourceImpl
import io.github.jan.kex.data.local.SchoolAuthenticationSettings
import io.github.jan.kex.data.local.SchoolAuthenticationSettingsImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single {
        AppDatabase(AndroidSqliteDriver(AppDatabase.Schema, androidContext(), "kex.db"))
    }
    single {
        Settings() as ObservableSettings
    }
    single<SchoolAuthenticationSettings> {
        SchoolAuthenticationSettingsImpl(get())
    }
    single<ExamDataSource> {
        ExamDataSourceImpl(get())
    }
}