package io.github.jan.kex.ui.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.jan.kex.R
import io.github.jan.kex.ui.icons.rememberMail
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.compose.auth.ui.password.OutlinedPasswordField

@OptIn(SupabaseExperimental::class, ExperimentalMaterial3Api::class)
@Composable
fun SchoolLoginDialog(
    login: (String, String) -> Unit,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
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
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(onDone = { login(username, password) }),
            label = { Text(text = stringResource(R.string.password)) },
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row {
            Button(onClick = { login(username, password) }) {
                Text(stringResource(R.string.login))
            }
            Spacer(modifier = Modifier.width(6.dp))
            Button(onClick = onDismiss) {
                Text(stringResource(R.string.later))
            }
        }
    }
}