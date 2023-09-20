package io.github.jan.kex.ui.screen.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.jan.kex.R
import io.github.jan.kex.ui.components.SettingsCard
import io.github.jan.kex.ui.dialog.LogoutDialog
import io.github.jan.kex.ui.icons.rememberLogout
import io.github.jan.kex.ui.screen.settings.entries.InformationEntry
import io.github.jan.kex.ui.screen.settings.entries.SchoolEntry
import io.github.jan.kex.ui.screen.settings.entries.ThemeEntry
import io.github.jan.kex.vm.AuthenticationViewModel
import io.github.jan.kex.vm.ExamViewModel
import io.github.jan.kex.vm.SettingsViewModel
import io.github.jan.kex.vm.SubjectViewModel
import io.github.jan.kex.vm.TaskViewModel
import io.github.jan.kex.vm.UpdateViewModel
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.composable.rememberSignOut
import org.koin.compose.koinInject

@OptIn(SupabaseExperimental::class, ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun SettingsScreen(
    authVm: AuthenticationViewModel,
    examVm: ExamViewModel,
    subjectVm: SubjectViewModel,
    taskVm: TaskViewModel,
    updateVm: UpdateViewModel,
    settingsVm: SettingsViewModel,
    composeAuth: ComposeAuth = koinInject()
) {
    var showLogoutDialog by remember { mutableStateOf(false) }
    val oldUsername by authVm.schoolUsername.collectAsStateWithLifecycle()
    val oldPassword by authVm.schoolPassword.collectAsStateWithLifecycle()
    var username by remember(oldUsername) { mutableStateOf(oldUsername ?: "") }
    var password by remember(oldPassword) { mutableStateOf(oldPassword ?: "") }
    val versionCheckResult by updateVm.versionDialogResult.collectAsStateWithLifecycle()
    val settingsEntries = remember { SettingsEntry.entries }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        items(settingsEntries, { it.name }) {
            var showEntry by remember { mutableStateOf(false) }
            Column(
                Modifier
                    .padding(12.dp)
                    .animateItemPlacement()
            ) {
                SettingsCard(
                    modifier = Modifier
                        .fillMaxWidth(),
                    title = stringResource(it.title),
                    leadingIcon = {
                        Icon(
                            it.icon(), null
                        )
                    }
                ) {
                    showEntry = !showEntry
                }
                AnimatedVisibility(visible = showEntry, Modifier.padding(top = 6.dp)) {
                    when(it) {
                        SettingsEntry.SchoolCredentials -> {
                            SchoolEntry(username, password, { username = it }, { password = it })
                        }
                        SettingsEntry.OpenAiCredentials -> {

                        }
                        SettingsEntry.Information -> {
                            InformationEntry(updateVm)
                        }
                        SettingsEntry.Theme -> {
                            ThemeEntry(settingsVm)
                        }
                    }
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        ExtendedFloatingActionButton(
            modifier = Modifier.padding(16.dp),
            onClick = { showLogoutDialog = true },
            text = { Text(stringResource(R.string.sign_out)) },
            icon = { Icon(rememberLogout(), contentDescription = null) },
            containerColor = MaterialTheme.colorScheme.errorContainer
        )
    }

    if(showLogoutDialog) {
        val logoutState = composeAuth.rememberSignOut()
        LogoutDialog(
            onLogout = {
                logoutState.startFlow()
                //authVm.logout() not neccessary as invoked by compose auth
                authVm.clearSchoolCredentials()
                examVm.clearLocalEntries()
                subjectVm.clearLocalEntries()
                taskVm.clearLocalEntries()
            },
            onClose = { showLogoutDialog = false }
        )
    }

    if(versionCheckResult != null) {
        AlertDialog(
            onDismissRequest = { updateVm.versionDialogResult.value = null },
            text = {
                Text(stringResource(versionCheckResult!!))
            },
            confirmButton = {
                TextButton(onClick = { updateVm.versionDialogResult.value = null }) {
                    Text("Ok")
                }
            }

        )
    }

    DisposableEffect(Unit) {
        onDispose {
            authVm.setSchoolCredentials(username.ifBlank { null }, password.ifBlank { null })
        }
    }
}