package io.github.jan.kex.di

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import io.github.jan.kex.data.LocalTask
import io.github.jan.kex.data.local.AppDatabase
import io.github.jan.kex.data.local.ExamDataSource
import io.github.jan.kex.data.local.ExamDataSourceImpl
import io.github.jan.kex.data.local.SchoolAuthenticationSettings
import io.github.jan.kex.data.local.SchoolAuthenticationSettingsImpl
import io.github.jan.kex.data.local.SubjectDataSource
import io.github.jan.kex.data.local.SubjectDataSourceImpl
import io.github.jan.kex.data.local.SubjectSuggestionDataSource
import io.github.jan.kex.data.local.SubjectSuggestionDataSourceImpl
import io.github.jan.kex.data.local.TaskDataSource
import io.github.jan.kex.data.local.TaskDataSourceImpl
import io.github.jan.kex.data.local.sqldelight.InstantAdapter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single {
        AppDatabase(AndroidSqliteDriver(AppDatabase.Schema, androidContext(), "kex.db"), LocalTask.Adapter(InstantAdapter, InstantAdapter))
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
    single<SubjectDataSource> {
        SubjectDataSourceImpl(get())
    }
    single<TaskDataSource> {
        TaskDataSourceImpl(get())
    }
    single<SubjectSuggestionDataSource> {
        SubjectSuggestionDataSourceImpl(get())
    }
}