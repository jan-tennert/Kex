package io.github.jan.kex.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.rememberOneTapSignInState
import io.github.jan.kex.BuildConfig
import io.github.jan.kex.ui.components.GoogleButton

private const val ONE_TAP_CLIENT_ID = BuildConfig.GOOGLE_CLIENT_ID

@Composable
fun AuthScreen(
    loggingIn: Boolean = false,
    onLoginWithIdToken: (String) -> Unit = {},
) {
    val state = rememberOneTapSignInState()
    OneTapSignInWithGoogle(
        state = state,
        clientId = ONE_TAP_CLIENT_ID,
        onTokenIdReceived = { tokenId ->
            onLoginWithIdToken(tokenId)
        },
        onDialogDismissed = { message ->
            Log.d("LOG", message)
        }
    )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if(loggingIn) {
            CircularProgressIndicator()
        } else {
            GoogleButton {
                state.open()
            }
        }
    }
}