package io.github.jan.kex.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import io.github.jan.kex.R
import io.github.jan.kex.ui.icons.rememberDateRange
import io.github.jan.kex.ui.screen.exam.noRippleClickable

@Composable
fun DatePickerField(selectedDate: String?,
                    modifier: Modifier = Modifier,
                    borderColor: Color = MaterialTheme.colorScheme.outline,
                    onClick: () -> Unit) {
    OutlinedTextField(
        value = selectedDate ?: "",
        onValueChange = {},
        enabled = false,
        label = { Text(stringResource(R.string.date)) },
        modifier = Modifier.noRippleClickable(onClick) then modifier,
        leadingIcon = { Icon(rememberDateRange(), contentDescription = null) },
        colors = OutlinedTextFieldDefaults.colors(
            disabledTextColor = MaterialTheme.colorScheme.onSurface,
            disabledBorderColor = borderColor,
            disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            //For Icons
            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    )
}