package io.github.jan.kex.ui.screen.exam

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedrejeb.richeditor.model.RichTextState
import io.github.jan.kex.R
import io.github.jan.kex.data.remote.Exam
import io.github.jan.kex.ui.components.SubjectField
import io.github.jan.kex.ui.components.ThemeEditor
import io.github.jan.kex.ui.icons.rememberNumbersIcon
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamEditScreen(
    exam: Exam,
    suggestions: List<String>,
    onEdit: (subject: String, theme: String?, points: String?) -> Unit
) {
    Column(
     //   modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(exam.subject, fontSize = 20.sp)
        Spacer(modifier = Modifier.padding(8.dp))
        var points by remember { mutableStateOf(exam.points?.toString() ?: "") }
        var subject by remember { mutableStateOf(TextFieldValue(exam.subject)) }
        val filteredSuggestions = remember(subject, suggestions) {
            if(subject.text.isNotBlank()) suggestions.filter { it.contains(subject.text) }.take(2) else emptyList()
        }
        var isError by remember { mutableStateOf(false) }
        val errorScope = rememberCoroutineScope()
        var selectedTopicModeIndex by remember { mutableStateOf(0) }
        val subjectTopicModes = remember {
            SubjectTopicMode.entries
        }
        val richTheme by remember { mutableStateOf(RichTextState().apply {
            exam.theme?.let { setHtml(it) }
        }) }
        val markdownTheme = remember { mutableStateOf(TextFieldValue(richTheme.toMarkdown())) }
        SubjectField(subject = subject, onSubjectChange = { subject = it }, isError = isError, suggestions = filteredSuggestions)
        OutlinedTextField(
            value = points,
            onValueChange = {
                if (it.toIntOrNull() != null || it.isBlank()) points = it
            },
            label = { Text(stringResource(R.string.score)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true,
            leadingIcon = { Icon(rememberNumbersIcon(), null) }
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
                if (subject.text.isNotBlank()) {
                    onEdit(subject.text, richTheme.toHtml().ifBlank { null }, points.ifBlank { null })
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
            Text(stringResource(id = R.string.save))
        }
    }
}