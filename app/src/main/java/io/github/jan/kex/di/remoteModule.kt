package io.github.jan.kex.di

import io.github.jan.kex.data.remote.AuthenticationApi
import io.github.jan.kex.data.remote.AuthenticationApiImpl
import io.github.jan.kex.data.remote.ExamApi
import io.github.jan.kex.data.remote.ExamApiImpl
import org.koin.dsl.module

val remoteModule = module {
    single<AuthenticationApi> {
        AuthenticationApiImpl(get())
    }
    single<ExamApi> {
        ExamApiImpl(get(), get(), get())
    }
}