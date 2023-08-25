package io.github.jan.kex.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.jan.kex.vm.AuthenticationViewModel
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.gotrue.SessionStatus
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.getViewModel
import org.koin.compose.koinInject

@Composable
fun RootScreen(
    authVM: AuthenticationViewModel = getViewModel()
) {
    val sessionStatus by authVM.sessionStatus.collectAsStateWithLifecycle()
    when(sessionStatus) {
        is SessionStatus.Authenticated -> {
            println(Json.encodeToString((sessionStatus as SessionStatus.Authenticated).session))
            AppScreen(authVm = authVM)
        }
        SessionStatus.LoadingFromStorage -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        else -> {
            AuthScreen(authVM = authVM)
        }
    }
}