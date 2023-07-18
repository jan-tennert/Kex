package io.github.jan.kex.di

import io.github.jan.kex.data.remote.AuthenticationApi
import io.github.jan.kex.data.remote.AuthenticationApiImpl
import io.github.jan.kex.data.remote.ExamApi
import io.github.jan.kex.data.remote.ExamApiImpl
import io.github.jan.kex.data.remote.SubjectApi
import io.github.jan.kex.data.remote.SubjectApiImpl
import io.github.jan.kex.data.remote.TaskApi
import io.github.jan.kex.data.remote.TaskApiImpl
import org.koin.dsl.module

val remoteModule = module {
    single<AuthenticationApi> {
        AuthenticationApiImpl(get())
    }
    single<ExamApi> {
        ExamApiImpl(get(), get(), get())
    }
    single<SubjectApi> {
        SubjectApiImpl(get(), get())
    }
    single<TaskApi> {
        TaskApiImpl(get(), get())
    }
}