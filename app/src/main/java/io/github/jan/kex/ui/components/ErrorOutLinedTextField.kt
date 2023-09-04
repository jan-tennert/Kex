package io.github.jan.kex.ui.components

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun ErrorOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit = {},
    enabled: Boolean = true,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    label: @Composable (() -> Unit)?,
    leadingIcon: @Composable (() -> Unit)?,
    singleLine: Boolean = false,
    defaultWidth: Dp = OutlinedTextFieldDefaults.MinWidth,
    errorExpandWidth: Dp = 30.dp,
    errorDisplayTime: Duration = 200.milliseconds,
    errorDisplayDelay: Duration = 0.milliseconds,
    displayError: Boolean = false
) {
    val transition = updateTransition(targetState = displayError, label = "transition")

    val widthOverride by transition.animateDp(transitionSpec = {
        tween(errorDisplayTime.inWholeMilliseconds.toInt(), errorDisplayDelay.inWholeMilliseconds.toInt())
    }, "") { animated ->
        if (animated) errorExpandWidth + defaultWidth else defaultWidth
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        label = label,
        modifier = Modifier.width(widthOverride) then modifier,
        leadingIcon = leadingIcon,
        colors = colors,
        singleLine = singleLine
    )
}
