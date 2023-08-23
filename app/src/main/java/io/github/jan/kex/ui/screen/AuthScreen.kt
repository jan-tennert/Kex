package io.github.jan.kex.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.jan.kex.R
import io.github.jan.kex.StringResource
import io.github.jan.kex.ui.components.GoogleButton
import io.github.jan.kex.vm.AuthenticationViewModel
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.rememberLoginWithGoogle
import io.github.jan.supabase.compose.auth.ui.ProviderButtonContent
import io.github.jan.supabase.gotrue.providers.Google
import org.koin.androidx.compose.getViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class, SupabaseExperimental::class)
@Composable
fun AuthScreen(
    authVM: AuthenticationViewModel = getViewModel(),
    composeAuth: ComposeAuth = koinInject()
) {
    val loggingIn by authVM.loggingIn.collectAsStateWithLifecycle()
    val authError by authVM.authError.collectAsStateWithLifecycle()
    val state = composeAuth.rememberLoginWithGoogle(
        onResult = {
            when (it) {
                NativeSignInResult.ClosedByUser -> authVM.authError.value =
                    StringResource(R.string.google_login_cancelled)

                is NativeSignInResult.Error -> authVM.authError.value =
                    StringResource(R.string.google_login_error, listOf(it.message))

                is NativeSignInResult.NetworkError -> authVM.authError.value =
                    StringResource(R.string.google_login_network_error, listOf(it.message))

                NativeSignInResult.Success -> Unit
            }
            authVM.loggingIn.value = false
        }
    )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (loggingIn) {
            CircularProgressIndicator()
        } else {
            OutlinedButton(
                onClick = {
                    authVM.loggingIn.value = true
                    state.startFlow()
                },
            ) {
                ProviderButtonContent(provider = Google)
            }
        }
    }
    if (authError != null) {
        AlertDialog(
            onDismissRequest = { authVM.authError.value = null },
            title = { Text(text = stringResource(id = R.string.error)) },
            text = {
                Text(
                    text = stringResource(
                        id = authError!!.id,
                        *authError!!.args.toTypedArray()
                    )
                )
            },
            confirmButton = {
                TextButton(onClick = { authVM.authError.value = null }) {
                    Text(text = "Ok")
                }
            }
        )
    }
}