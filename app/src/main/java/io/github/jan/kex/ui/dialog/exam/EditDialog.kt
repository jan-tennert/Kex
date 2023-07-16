package io.github.jan.kex.ui.dialog.exam

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import io.github.jan.kex.R
import io.github.jan.kex.data.remote.Exam
import io.github.jan.kex.ui.icons.rememberNumbers
import io.github.jan.kex.ui.icons.rememberSubject
import io.github.jan.kex.ui.icons.rememberTypeSpecimen

@Composable
fun EditExamDialog(
    exam: Exam,
    onClose: () -> Unit,
    onEdit: (subject: String, theme: String?, points: String?) -> Unit
) {
    Dialog(onDismissRequest = onClose) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(exam.subject, fontSize = 20.sp)
            Spacer(modifier = Modifier.padding(8.dp))
            var theme by remember { mutableStateOf(exam.theme ?: "") }
            var points by remember { mutableStateOf(exam.points?.toString() ?: "") }
            var subject by remember { mutableStateOf(exam.subject) }
            OutlinedTextField(
                value = subject,
                onValueChange = { subject = it },
                label = { Text(stringResource(R.string.subject)) },
                singleLine = true,
                leadingIcon = { Icon(rememberTypeSpecimen(), null)}
            )
            OutlinedTextField(
                value = theme,
                onValueChange = { theme = it },
                label = { Text(stringResource(R.string.theme)) },
                minLines = 2,
                leadingIcon = { Icon(rememberSubject(), null)}
            )
            OutlinedTextField(
                value = points,
                onValueChange = {
                    if (it.toIntOrNull() != null || it.isBlank()) points = it
                },
                label = { Text(stringResource(R.string.score)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true,
                leadingIcon = { Icon(rememberNumbers(), null)}
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    onEdit(subject, theme.ifBlank { null }, points.ifBlank { null })
                    onClose()
                },
                enabled = subject.isNotBlank()
            ) {
                Text(stringResource(id = R.string.save))
            }
        }
    }
}