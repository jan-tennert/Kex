package io.github.jan.kex.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import io.github.jan.kex.BuildConfig
import io.github.jan.kex.ui.dialog.UpdateDialog
import io.github.jan.kex.ui.screen.settings.LocalKexTheme
import io.github.jan.kex.vm.AuthenticationViewModel
import io.github.jan.kex.vm.SettingsViewModel
import io.github.jan.kex.vm.UpdateViewModel
import io.github.jan.supabase.auth.status.SessionStatus
import io.github.z4kn4fein.semver.Version
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RootScreen(
    authVM: AuthenticationViewModel = koinViewModel(),
    updateVm: UpdateViewModel = koinViewModel(),
    settingsVm: SettingsViewModel = koinViewModel()
) {
    val sessionStatus by authVM.sessionStatus.collectAsStateWithLifecycle()
    val currentTheme by settingsVm.currentTheme.collectAsState()
    CompositionLocalProvider(LocalKexTheme provides currentTheme) {
        when(sessionStatus) {
            is SessionStatus.Authenticated, is SessionStatus.RefreshFailure -> {
                AppScreen(authVm = authVM, updateVm = updateVm, settingsVm = settingsVm)
            }
            SessionStatus.Initializing -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            else -> {
                AuthScreen(authVM = authVM)
            }
        }
        VersionCheck(updateVm)
    }
}

@Composable
fun VersionCheck(
    updateVm: UpdateViewModel = koinViewModel()
) {
    val latestVersion by updateVm.latestVersion.collectAsState()
    val currentVersion = remember { Version.parse(BuildConfig.VERSION_NAME) }
    val ignoreUpdate by updateVm.ignoreUpdate.collectAsState()
    if(latestVersion != null && latestVersion!! > currentVersion && !ignoreUpdate) {
        UpdateDialog(latestVersion!!, updateVm = updateVm)
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                updateVm.checkForUpdates(true)
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}