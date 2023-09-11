package io.github.jan.kex.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.mohamedrejeb.richeditor.model.RichTextState
import com.mohamedrejeb.richeditor.ui.material3.OutlinedRichTextEditor
import com.mohamedrejeb.richeditor.ui.material3.RichText
import io.github.jan.kex.R
import io.github.jan.kex.ui.dialog.AddLinkDialog
import io.github.jan.kex.ui.screen.exam.SubjectTopicMode
import io.github.jan.kex.ui.screen.settings.LocalKexTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.ThemeEditor(
    richTheme: RichTextState,
    markdownTheme: MutableState<TextFieldValue>,
    subjectTopicModes: List<SubjectTopicMode>,
    selectedTopicModeIndex: Int,
    onTopicModeChange: (Int) -> Unit
) {
    var showAddLinkDialog by remember { mutableStateOf(false) }
    val primaryColor = MaterialTheme.colorScheme.primary
    LaunchedEffect(LocalKexTheme.current, isSystemInDarkTheme()) {
        richTheme.setConfig(
            linkColor = primaryColor
        )
    }
    when(subjectTopicModes[selectedTopicModeIndex]) {
        SubjectTopicMode.VISUAL -> {
            RichTextStyleRow(state = richTheme) {
                showAddLinkDialog = true
            }
            OutlinedRichTextEditor(
                state = richTheme,
                label = { Text(stringResource(R.string.theme)) },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
        SubjectTopicMode.MARKDOWN -> {
            OutlinedTextField(
                value = markdownTheme.value,
                onValueChange = { markdownTheme.value = it },
                label = { Text(stringResource(R.string.theme)) },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
        SubjectTopicMode.PREVIEW -> {
            ElevatedCard(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(8.dp)) {
                RichText(
                    state = richTheme,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    SingleChoiceSegmentedButtonRow {
        subjectTopicModes.forEachIndexed { index, option ->
            SegmentedButton(
                selected = selectedTopicModeIndex == index,
                onClick = {
                    val current = subjectTopicModes[selectedTopicModeIndex]
                    if (current == SubjectTopicMode.MARKDOWN) {
                        richTheme.setMarkdown(markdownTheme.value.text)
                    } else if (option == SubjectTopicMode.MARKDOWN) {
                        markdownTheme.value = TextFieldValue(richTheme.toMarkdown(), TextRange(richTheme.toMarkdown().length))
                    }
                    onTopicModeChange(index)
                },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = subjectTopicModes.size)
            ) {
                Text(option.title)
            }
        }
    }
    if(showAddLinkDialog) {
        AddLinkDialog(
            onClose = {
                showAddLinkDialog = false
            },
            onSuccess = { text, link ->
                richTheme.addLink(text, link)
                showAddLinkDialog = false
            }
        )
    }
}