package io.github.jan.kex.ui.screen.exam

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.mohamedrejeb.richeditor.model.RichTextState
import com.mohamedrejeb.richeditor.ui.material3.OutlinedRichTextEditor
import io.github.jan.kex.R
import io.github.jan.kex.data.remote.Exam
import io.github.jan.kex.ui.components.DatePickerField
import io.github.jan.kex.ui.components.DropDownField
import io.github.jan.kex.ui.components.RichTextStyleRow
import io.github.jan.kex.ui.components.SubjectField
import io.github.jan.kex.ui.icons.rememberDone
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamCreateScreen(
    suggestions: List<String>,
    onCreate: (subject: String, date: String, theme: String, type: Exam.Type) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var subject by remember { mutableStateOf(TextFieldValue()) }
        val filteredSuggestions = remember(subject, suggestions) {
            if(subject.text.isNotBlank()) suggestions.filter { it.contains(subject.text) }.take(2) else emptyList()
        }
        var expandSuggestions by remember { mutableStateOf(false) }
        val datePickerState = rememberDatePickerState()
        var showDatePicker by remember { mutableStateOf(false) }
        val theme by remember { mutableStateOf(RichTextState()) }
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
        var isError by remember { mutableStateOf(false) }
        val errorScope = rememberCoroutineScope()
        SubjectField(subject = subject, onSubjectChange = { subject = it }, isError = isError, suggestions = filteredSuggestions)
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
        DatePickerField(
            selectedDate = selectedDate,
            onClick = { showDatePicker = true },
            displayError = isError && selectedDate == null
        )
        RichTextStyleRow(state = theme)
        OutlinedRichTextEditor(
            state = theme,
            label = { Text(stringResource(R.string.theme)) },
            //     leadingIcon = { Icon(rememberSubject(), null)},
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)        )
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
        Button(
            onClick = {
                if (selectedDate != null && subject.text.isNotBlank()) {
                    onCreate(subject.text, selectedDate, theme.toHtml(), type)
                } else {
                    errorScope.launch {
                        isError = true
                        delay(500)
                        isError =false
                    }
                }
            },
            modifier = Modifier
                .padding(12.dp)
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