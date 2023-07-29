package io.github.jan.kex.ui.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.jan.kex.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogoutDialog(
    onLogout: () -> Unit,
    onClose: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onClose,
        text = {
            Text(stringResource(R.string.sign_out_text))
        },
        dismissButton = {
            TextButton(onClick = onClose) {
                Text(stringResource(R.string.cancel))
            }
        },
        confirmButton = {
            TextButton(onClick = onLogout) {
                Text(stringResource(R.string.sign_out))
            }
        }
    )
}