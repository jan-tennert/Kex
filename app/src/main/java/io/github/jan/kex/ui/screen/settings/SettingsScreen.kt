package io.github.jan.kex.ui.screen.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
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
import io.github.jan.kex.BuildConfig
import io.github.jan.kex.R
import io.github.jan.kex.ui.components.SettingsCard
import io.github.jan.kex.ui.dialog.LogoutDialog
import io.github.jan.kex.ui.icons.rememberLogout
import io.github.jan.kex.ui.icons.rememberMail
import io.github.jan.kex.vm.AuthenticationViewModel
import io.github.jan.kex.vm.ExamViewModel
import io.github.jan.kex.vm.SettingsViewModel
import io.github.jan.kex.vm.SubjectViewModel
import io.github.jan.kex.vm.TaskViewModel
import io.github.jan.kex.vm.UpdateViewModel
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.composable.rememberSignOut
import io.github.jan.supabase.compose.auth.ui.password.OutlinedPasswordField
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

    val settingsEntries = remember { SettingsEntry.entries }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
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
                            Column {
                                OutlinedTextField(
                                    value = username,
                                    onValueChange = { username = it },
                                    label = { Text(text = stringResource(R.string.username)) },
                                    leadingIcon = { Icon(rememberMail(), contentDescription = null) },
                                    singleLine = true,
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                OutlinedPasswordField(
                                    value = password,
                                    onValueChange = { password = it },
                                    label = { Text(text = stringResource(R.string.password)) },
                                    mandatory = false
                                )
                            }
                        }
                        SettingsEntry.OpenAiCredentials -> {

                        }
                        SettingsEntry.Information -> {
                            Column {
                                Text(stringResource(R.string.current_version, BuildConfig.VERSION_NAME))
                                Spacer(modifier = Modifier.height(6.dp))
                                Button(onClick = { updateVm.ignoreUpdate.value = false; updateVm.checkForUpdates() }) {
                                    Text(stringResource(R.string.check_for_updates))
                                }
                            }
                        }
                        SettingsEntry.Theme -> {
                            val themes = remember { KexTheme.entries }
                            val currentTheme = LocalKexTheme.current
                            Column {
                                themes.forEach { theme ->
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        RadioButton(selected = currentTheme == theme, onClick = { settingsVm.setTheme(theme) })
                                     //   Icon(theme.icon(), contentDescription = null)
                                        Text(stringResource(theme.title))
                                    }
                                }
                            }
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

    DisposableEffect(Unit) {
        onDispose {
            authVm.setSchoolCredentials(username.ifBlank { null }, password.ifBlank { null })
        }
    }
}