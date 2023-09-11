package io.github.jan.kex.ui.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import io.github.jan.kex.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLinkDialog(onClose: () -> Unit, onSuccess: (text: String, link: String) -> Unit) {
    var text by remember { mutableStateOf("") }
    var link by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onClose,
        text = {
            Column {
                OutlinedTextField(value = text, onValueChange = { text = it }, label = { Text("Text") })
                OutlinedTextField(value = link, onValueChange = { link = it }, label = { Text("Link") })
            }
        },
        confirmButton = { TextButton(onClick = { onSuccess(text, link) }) { Text("Ok") } },
        dismissButton = { TextButton(onClick = onClose) { Text(stringResource(id = R.string.cancel)) } }
    )
}