package io.github.jan.kex.ui.screen.exam

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.jan.kex.R
import io.github.jan.kex.data.remote.Exam
import io.github.jan.kex.ui.components.DropDownField
import io.github.jan.kex.ui.icons.rememberDateRange
import io.github.jan.kex.ui.icons.rememberDone
import io.github.jan.kex.ui.icons.rememberSubject
import io.github.jan.kex.ui.icons.rememberTypeSpecimen
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamCreateScreen(
    onCreate: (subject: String, date: String, theme: String, type: Exam.Type) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var subject by remember { mutableStateOf("") }
        val datePickerState = rememberDatePickerState()
        var showDatePicker by remember { mutableStateOf(false) }
        var theme by remember { mutableStateOf("") }
        val selectedDate = remember(datePickerState, showDatePicker) {
            datePickerState.selectedDateMillis?.let {
                val date = Instant.fromEpochMilliseconds(it).toLocalDateTime(
                    TimeZone.currentSystemDefault()
                )
                "${date.dayOfMonth}.${date.monthNumber}.${date.year}"
            }
        }
        var type by remember {
            mutableStateOf(Exam.Type.EXAM)
        }
        var expandTypeField by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = subject,
            onValueChange = { subject = it },
            label = { Text(stringResource(R.string.subject)) },
            leadingIcon = { Icon(rememberTypeSpecimen(), contentDescription = null) },
            singleLine = true
        )
        Spacer(modifier = Modifier.padding(8.dp))
        DropDownField(
            expanded = expandTypeField,
            value = stringResource(type.nameId),
            onExpandChange = { expandTypeField = it },
            label = { Text(stringResource(R.string.type)) },
        ) {
            Exam.Type.entries.forEach {
                DropdownMenuItem(text = { Text(stringResource(it.nameId))}, onClick = { type = it; expandTypeField = false })
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = selectedDate ?: "",
            onValueChange = {},
            enabled = false,
            label = { Text(stringResource(R.string.date)) },
            modifier = Modifier.noRippleClickable { showDatePicker = true },
            leadingIcon = { Icon(rememberDateRange(), contentDescription = null) },
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledBorderColor = MaterialTheme.colorScheme.outline,
                disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                //For Icons
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = theme,
            onValueChange = { theme = it },
            label = { Text(stringResource(R.string.theme)) },
            leadingIcon = { Icon(rememberSubject(), contentDescription = null) },
            minLines = 2
        )
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
        Spacer(Modifier.weight(1f))
        Button(
            onClick = { onCreate(subject, selectedDate!!, theme, type)},
            modifier = Modifier
         //       .align(Alignment.Center)
                .padding(16.dp)
        ) {
            Icon(rememberDone(), contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(stringResource(R.string.save))
        }
    }
}

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier =
    composed {
        clickable(indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
            onClick()
        }
    }