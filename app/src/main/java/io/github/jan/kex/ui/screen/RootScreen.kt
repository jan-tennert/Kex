package io.github.jan.kex.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.jan.kex.vm.AuthenticationViewModel
import io.github.jan.supabase.gotrue.SessionStatus
import org.koin.androidx.compose.getViewModel

@Composable
fun RootScreen(
    authVM: AuthenticationViewModel = getViewModel()
) {
    val sessionStatus by authVM.sessionStatus.collectAsState()
    val loggingIn by authVM.loggingIn.collectAsState()
    when(sessionStatus) {
        is SessionStatus.Authenticated -> {
            AppScreen(authVm = authVM)
        }
        SessionStatus.LoadingFromStorage -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        else -> {
            AuthScreen(loggingIn, authVM::loginWithIdToken) {
                authVM.loggingIn.value = it
            }
        }
    }
}