package io.github.jan.kex.di

import io.github.jan.kex.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.functions.Functions
import io.github.jan.supabase.functions.functions
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val supabaseModule = module {
    single {
        Json {
            ignoreUnknownKeys = true
        }
    }
    single {
        createSupabaseClient(
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_KEY
        ) {
            defaultSerializer = KotlinXSerializer(get())
            install(GoTrue)
            install(Postgrest)
            install(Functions)
        }
    }
    single {
        get<SupabaseClient>().gotrue
    }
    single {
        get<SupabaseClient>().postgrest
    }
    single {
        get<SupabaseClient>().functions
    }
}