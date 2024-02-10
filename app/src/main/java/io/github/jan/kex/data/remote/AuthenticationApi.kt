package io.github.jan.kex.data.remote

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.IDTokenProvider
import io.github.jan.supabase.gotrue.providers.builtin.IDToken
import kotlinx.coroutines.flow.StateFlow

interface AuthenticationApi {

    val sessionStatus: StateFlow<SessionStatus>

    suspend fun loginWithIdToken(idToken: String, provider: IDTokenProvider = Google)

    suspend fun logout()

    suspend fun loginWithGoogle()

}

internal class AuthenticationApiImpl(
    private val supabaseClient: SupabaseClient
): AuthenticationApi {

    override val sessionStatus: StateFlow<SessionStatus> = supabaseClient.auth.sessionStatus

    override suspend fun loginWithIdToken(idToken: String, provider: IDTokenProvider) {
        supabaseClient.auth.signInWith(IDToken) {
            this.idToken = idToken
            this.provider = provider
        }
    }

    override suspend fun logout() {
        supabaseClient.auth.signOut()
    }

    override suspend fun loginWithGoogle() {
        supabaseClient.auth.signInWith(Google)
    }

}