package io.github.jan.kex.ui.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.jan.kex.data.remote.UpdateDownloadEvent
import io.github.jan.kex.vm.UpdateViewModel
import io.github.z4kn4fein.semver.Version

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateDialog(version: Version, updateVm: UpdateViewModel) {
    val downloadStatus by updateVm.downloadStatus.collectAsStateWithLifecycle()
    when(downloadStatus) {
        UpdateDownloadEvent.Finished -> {
            AlertDialog(
                onDismissRequest = {},
                text = { Text(text = "Update downloaded! Install it now?") },
                confirmButton = {
                    TextButton(onClick = { updateVm.installLatestVersion() }) {
                        Text("Install")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { updateVm.ignoreUpdate.value = true }) {
                        Text("Ignore")
                    }
                }
            )
        }
        is UpdateDownloadEvent.Progress -> {
            Dialog(onDismissRequest = {}) {
                Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
                    LinearProgressIndicator((downloadStatus as UpdateDownloadEvent.Progress).progress)
                }
            }
        }
        null -> {
            AlertDialog(
                onDismissRequest = {},
                text = { Text(text = "New Update available: $version") },
                confirmButton = {
                    TextButton(onClick = { updateVm.downloadLatestVersion() }) {
                        Text("Download")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { updateVm.ignoreUpdate.value = true }) {
                        Text("Ignore")
                    }
                }
            )
        }
    }
}