package io.github.jan.kex.ui.screen.exam.importer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.jan.kex.R
import io.github.jan.kex.ui.components.DropDownField
import io.github.jan.kex.ui.icons.rememberRefreshIcon


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamPlanKeySelection(
    keys: List<String>,
    loading: Boolean,
    refresh: () -> Unit,
    onSubmit: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        var selectedKey by remember { mutableStateOf("") }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            var expanded by remember { mutableStateOf(false) }
            DropDownField(
                expanded = expanded,
                onExpandChange = { expanded = it },
                value = selectedKey,
                placeholder = { Text(stringResource(R.string.select_exam_plan)) }
            ) {
                keys.forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = { selectedKey = it; expanded = false },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
            Box(contentAlignment = Alignment.Center, modifier = Modifier
                .padding(8.dp)
                .size(24.dp)) {
                CircularProgressIndicator(
                    Modifier
                        .alpha(if (loading) 1f else 0f)
                        .matchParentSize())
                IconButton(
                    onClick = refresh, modifier = Modifier
                        .alpha(if (!loading) 1f else 0f)
                        .matchParentSize()
                ) {
                    Icon(rememberRefreshIcon(), contentDescription = "Refresh")
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { onSubmit(selectedKey) }) {
            Text(stringResource(R.string.continueA))
        }
    }
}