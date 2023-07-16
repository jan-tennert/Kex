package io.github.jan.kex

import android.app.Application
import io.github.jan.kex.di.appModule
import io.github.jan.kex.di.localModule
import io.github.jan.kex.di.supabaseModule
import io.github.jan.kex.di.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(remoteModule, localModule, supabaseModule, appModule)
        }
    }

}