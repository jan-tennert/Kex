package io.github.jan.kex.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.jan.kex.R
import io.github.jan.kex.ui.icons.rememberDateRange
import io.github.jan.kex.ui.screen.exam.noRippleClickable

@Composable
fun DatePickerField(
    selectedDate: String?,
    displayError: Boolean = false,
    onClick: () -> Unit,
) {
    ErrorOutLinedTextField(
        value = selectedDate ?: "",
        label = { Text(stringResource(R.string.date)) },
        modifier = Modifier.noRippleClickable(onClick),
        leadingIcon = { Icon(rememberDateRange(), contentDescription = null) },
        displayError = displayError,
        enabled = false
    )
}