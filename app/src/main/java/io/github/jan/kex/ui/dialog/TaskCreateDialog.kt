package io.github.jan.kex.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import io.github.jan.kex.R
import io.github.jan.kex.data.remote.Task
import io.github.jan.kex.ui.components.DatePickerField
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskCreateDialog(task: Task?, onDismiss: () -> Unit, onCreate: (task: String, dueDate: Instant) -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10))
                .padding(16.dp)
        ) {
            var newTask by remember { mutableStateOf(task?.task ?: "") }
            val datePickerState = rememberDatePickerState(task?.dueDate?.toEpochMilliseconds())
            var showDatePicker by remember { mutableStateOf(false) }
            val selectedDate = remember(datePickerState, showDatePicker) {
                datePickerState.selectedDateMillis?.let {
                    val date = Instant.fromEpochMilliseconds(it).toLocalDateTime(
                        TimeZone.currentSystemDefault()
                    )
                    "${date.dayOfMonth}.${date.monthNumber}.${date.year}"
                }
            }
            Text(stringResource(R.string.create_task), style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 8.dp))
            OutlinedTextField(value = newTask, onValueChange = { newTask = it }, label = {
                Text(
                    stringResource(R.string.task)
                )
            })
            DatePickerField(selectedDate) { showDatePicker = true }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                onCreate(
                    newTask,
                    Instant.fromEpochMilliseconds(datePickerState.selectedDateMillis!!)
                ); onDismiss()
            }, enabled = selectedDate != null) {
                Text(stringResource(R.string.save))
            }
            if (showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        TextButton(onClick = { showDatePicker = false }) {
                            Text("Ok")
                        }
                    }
                ) {
                    DatePicker(
                        state = datePickerState
                    )
                }
            }
        }
    }
}