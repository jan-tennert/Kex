package io.github.jan.kex.ui.screen.exam

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import io.github.jan.kex.R
import io.github.jan.kex.data.remote.Exam
import io.github.jan.kex.ui.components.DatePickerField
import io.github.jan.kex.ui.components.DropDownField
import io.github.jan.kex.ui.components.SubjectField
import io.github.jan.kex.ui.components.ThemeEditor
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
            if (subject.text.isNotBlank()) suggestions.filter {
                it.contains(
                    subject.text,
                    ignoreCase = true
                )
            }.take(2) else emptyList()
        }
        val datePickerState = rememberDatePickerState()
        var showDatePicker by remember { mutableStateOf(false) }

        val richTheme = rememberRichTextState()
        val markdownTheme = remember { mutableStateOf(TextFieldValue()) }

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
        var selectedTopicModeIndex by remember { mutableStateOf(0) }
        val subjectTopicModes = remember {
            SubjectTopicMode.entries
        }
        val errorScope = rememberCoroutineScope()
        SubjectField(
            subject = subject,
            onSubjectChange = { subject = it },
            isError = isError,
            suggestions = filteredSuggestions
        )
        DropDownField(
            expanded = expandTypeField,
            value = stringResource(type.nameId),
            onExpandChange = { expandTypeField = it },
            label = { Text(stringResource(R.string.type)) },
        ) {
            Exam.Type.entries.forEach {
                DropdownMenuItem(
                    text = { Text(stringResource(it.nameId)) },
                    onClick = { type = it; expandTypeField = false })
            }
        }
        DatePickerField(
            selectedDate = selectedDate,
            onClick = { showDatePicker = true },
            displayError = isError && selectedDate == null
        )
        ThemeEditor(
            richTheme = richTheme,
            markdownTheme = markdownTheme,
            subjectTopicModes = subjectTopicModes,
            selectedTopicModeIndex = selectedTopicModeIndex,
            onTopicModeChange = { selectedTopicModeIndex = it }
        )
        Button(
            onClick = {
                if (selectedDate != null && subject.text.isNotBlank()) {
                    if(subjectTopicModes[selectedTopicModeIndex] == SubjectTopicMode.MARKDOWN) {
                        richTheme.setMarkdown(markdownTheme.value.text)
                    }
                    onCreate(subject.text, selectedDate, richTheme.toHtml(), type)
                } else {
                    errorScope.launch {
                        isError = true
                        delay(500)
                        isError = false
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

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier =
    composed {
        clickable(indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
            onClick()
        }
    }