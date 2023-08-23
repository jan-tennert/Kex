package io.github.jan.kex.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.jan.kex.R
import io.github.jan.kex.ui.dialog.LogoutDialog
import io.github.jan.kex.ui.icons.rememberLogout
import io.github.jan.kex.vm.AuthenticationViewModel
import io.github.jan.kex.vm.ExamViewModel
import io.github.jan.kex.vm.SubjectViewModel
import io.github.jan.kex.vm.TaskViewModel
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.composable.rememberSignOut
import org.koin.compose.koinInject

@Composable
fun SettingsScreen(
    authVm: AuthenticationViewModel,
    examVm: ExamViewModel,
    subjectVm: SubjectViewModel,
    taskVm: TaskViewModel,
    composeAuth: ComposeAuth = koinInject()
) {
    var showLogoutDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Row {
            Spacer(Modifier.weight(1f))
            ExtendedFloatingActionButton(
                onClick = { showLogoutDialog = true },
                text = { Text(stringResource(R.string.sign_out)) },
                icon = { Icon(rememberLogout(), contentDescription = null) },
                containerColor = MaterialTheme.colorScheme.errorContainer
            )
        }
    }

    if(showLogoutDialog) {
        val logoutState = composeAuth.rememberSignOut()
        LogoutDialog(
            onLogout = {
                logoutState.startFlow()
                authVm.logout()
                examVm.clearLocalEntries()
                subjectVm.clearLocalEntries()
                taskVm.clearLocalEntries()
            },
            onClose = { showLogoutDialog = false }
        )
    }
}