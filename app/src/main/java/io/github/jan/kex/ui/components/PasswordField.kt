package io.github.jan.kex.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import io.github.jan.kex.ui.icons.KeyIcon
import io.github.jan.kex.ui.icons.VisibilityIcon
import io.github.jan.kex.ui.icons.VisibilityOffIcon

@Composable
fun PasswordField(
    password: String,
    onPasswordChanged: (String) -> Unit,
    label: String = "Password",
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    var visible by remember { mutableStateOf(false) }
    OutlinedTextField(
        password,
        onValueChange = onPasswordChanged,
        leadingIcon = { Icon(KeyIcon, "Password") },
        label = { Text(label) },
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = imeAction),
        keyboardActions = keyboardActions,
        singleLine = true,
        trailingIcon = {
            IconButton({
                visible = !visible
            }) {
                Icon(if(visible) VisibilityIcon else VisibilityOffIcon, "", tint = MaterialTheme.colorScheme.onBackground)
            }
        },
        modifier = modifier
    )
}