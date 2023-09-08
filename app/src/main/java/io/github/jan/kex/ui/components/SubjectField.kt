package io.github.jan.kex.ui.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import io.github.jan.kex.R
import io.github.jan.kex.ui.icons.rememberTypeSpecimen
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectField(
    subject: TextFieldValue,
    onSubjectChange: (TextFieldValue) -> Unit,
    isError: Boolean,
    suggestions: List<String>
) {
    var expandSuggestions by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(expanded = expandSuggestions, onExpandedChange = { expandSuggestions = it }) {
        ErrorOutlinedTextField(
            modifier = Modifier.menuAnchor(),
            value = subject,
            onValueChange = { onSubjectChange(it); expandSuggestions = true },
            label = { Text(stringResource(R.string.subject)) },
            leadingIcon = { Icon(rememberTypeSpecimen(), contentDescription = null) },
            singleLine = true,
            errorDisplayDelay = 150.milliseconds,
            displayError = isError && subject.text.isBlank()
        )
        ExposedDropdownMenu(
            expanded = suggestions.isNotEmpty() && subject.text.isNotBlank() && expandSuggestions,
            onDismissRequest = { expandSuggestions = false }
        ) {
            suggestions.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        onSubjectChange(TextFieldValue(it, TextRange(it.length)));
                        expandSuggestions = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}