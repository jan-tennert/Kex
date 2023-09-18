package io.github.jan.kex.ui.dialog

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import io.github.jan.kex.R

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NotificationPermissionDialog(disable: () -> Unit) {
    val permissionState = rememberPermissionState(Manifest.permission.POST_NOTIFICATIONS)
    AlertDialog(
        onDismissRequest = disable,
        title = { Text(text = stringResource(id = R.string.notifications)) },
        text = { Text(text = stringResource(id = R.string.notifications_text)) },
        confirmButton = {
            TextButton(onClick = {
                permissionState.launchPermissionRequest()
                disable()
            }) {
                Text(stringResource(R.string.yes))
            }
        },
        dismissButton = {
            TextButton(onClick = disable) {
                Text(stringResource(R.string.no))
            }
        }
    )
}