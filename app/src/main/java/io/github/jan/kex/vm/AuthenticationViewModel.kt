package io.github.jan.kex.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.kex.data.local.SchoolAuthenticationSettings
import io.github.jan.kex.data.remote.AuthenticationApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthenticationViewModel(
    private val authenticationApi: AuthenticationApi,
    private val schoolAuthenticationSettings: SchoolAuthenticationSettings
): ViewModel() {

    val sessionStatus = authenticationApi.sessionStatus
    val loggingIn = MutableStateFlow(false)
    val schoolUsername = schoolAuthenticationSettings.username
    val schoolPassword = schoolAuthenticationSettings.password

    fun loginWithIdToken(idToken: String) {
        loggingIn.value = true
        viewModelScope.launch {
            kotlin.runCatching {
                authenticationApi.loginWithIdToken(idToken)
            }.onFailure {
                Log.e("LOG", "Failed to login", it)
            }
            loggingIn.value = false
        }
    }

    fun setSchoolCredentials(username: String, password: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                schoolAuthenticationSettings.setUsername(username)
                schoolAuthenticationSettings.setPassword(password)
            }.onFailure {
                Log.e("LOG", "Failed to set school credentials", it)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            kotlin.runCatching {
                authenticationApi.logout()
            }.onFailure {
                Log.e("LOG", "Failed to logout", it)
            }
        }
    }

}