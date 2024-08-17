package io.github.jan.kex.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import io.github.jan.kex.R
import io.github.jan.kex.ui.icons.rememberMail
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.compose.auth.ui.annotations.AuthUiExperimental
import io.github.jan.supabase.compose.auth.ui.password.OutlinedPasswordField

@OptIn(SupabaseExperimental::class, ExperimentalMaterial3Api::class, AuthUiExperimental::class)
@Composable
fun SchoolLoginDialog(login: (String, String) -> Unit, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            Text(stringResource(R.string.enter_school_credentials), fontSize = 20.sp)
            Spacer(Modifier.height(6.dp))
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = stringResource(R.string.username)) },
                leadingIcon = { Icon(rememberMail(), contentDescription = null) },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedPasswordField(
                value = password,
                onValueChange = { password = it },
                keyboardOptions =
                    KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                keyboardActions = KeyboardActions(onDone = { login(username, password) }),
                label = { Text(text = stringResource(R.string.password)) },
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row {
                TextButton(onClick = { login(username, password) }) {
                    Text(stringResource(R.string.login))
                }
                Spacer(modifier = Modifier.width(6.dp))
                TextButton(onClick = onDismiss) { Text(stringResource(R.string.later)) }
            }
        }
    }
}
