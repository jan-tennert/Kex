package io.github.jan.kex.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.jan.kex.R
import io.github.jan.kex.ui.dialog.LogoutDialog
import io.github.jan.kex.ui.icons.rememberLogout
import io.github.jan.kex.ui.icons.rememberMail
import io.github.jan.kex.vm.AuthenticationViewModel
import io.github.jan.kex.vm.ExamViewModel
import io.github.jan.kex.vm.SubjectViewModel
import io.github.jan.kex.vm.TaskViewModel
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.composable.rememberSignOut
import io.github.jan.supabase.compose.auth.ui.password.OutlinedPasswordField
import org.koin.compose.koinInject

@OptIn(SupabaseExperimental::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    authVm: AuthenticationViewModel,
    examVm: ExamViewModel,
    subjectVm: SubjectViewModel,
    taskVm: TaskViewModel,
    composeAuth: ComposeAuth = koinInject()
) {
    var showLogoutDialog by remember { mutableStateOf(false) }
    val oldUsername by authVm.schoolUsername.collectAsStateWithLifecycle()
    val oldPassword by authVm.schoolPassword.collectAsStateWithLifecycle()
    var username by remember(oldUsername) { mutableStateOf(oldUsername) }
    var password by remember(oldPassword) { mutableStateOf(oldPassword) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(stringResource(R.string.settings_school_credentials), style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = stringResource(R.string.username)) },
            leadingIcon = { Icon(rememberMail(), contentDescription = null) },
            singleLine = true,
        )
        OutlinedPasswordField(
            value = password,
            onValueChange = { password = it },
            mandatory = false
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(R.string.settings_openai), style = MaterialTheme.typography.headlineSmall)
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
                //authVm.logout() not neccessary as invoked by compose auth
                examVm.clearLocalEntries()
                subjectVm.clearLocalEntries()
                taskVm.clearLocalEntries()
            },
            onClose = { showLogoutDialog = false }
        )
    }
}