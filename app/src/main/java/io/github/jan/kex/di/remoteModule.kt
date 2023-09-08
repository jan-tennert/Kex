package io.github.jan.kex.di

import io.github.jan.kex.data.remote.AuthenticationApi
import io.github.jan.kex.data.remote.AuthenticationApiImpl
import io.github.jan.kex.data.remote.ExamApi
import io.github.jan.kex.data.remote.ExamApiImpl
import io.github.jan.kex.data.remote.GithubApi
import io.github.jan.kex.data.remote.GithubApiImpl
import io.github.jan.kex.data.remote.SubjectApi
import io.github.jan.kex.data.remote.SubjectApiImpl
import io.github.jan.kex.data.remote.TaskApi
import io.github.jan.kex.data.remote.TaskApiImpl
import io.github.jan.kex.data.remote.UpdateManager
import io.github.jan.kex.data.remote.UpdateManagerImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

val remoteModule = module {
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }
    }
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
    single<GithubApi> {
        GithubApiImpl(get())
    }
    single<UpdateManager> {
        UpdateManagerImpl(get(), get())
    }
}