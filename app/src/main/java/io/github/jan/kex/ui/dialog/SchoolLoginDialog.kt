package io.github.jan.kex.ui.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.jan.kex.R
import io.github.jan.kex.ui.components.PasswordField
import io.github.jan.kex.ui.icons.rememberMail

@Composable
fun SchoolLoginDialog(login: (String, String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        Text(stringResource(R.string.enter_school_credentials), fontSize = 20.sp)
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = stringResource(R.string.username)) },
            leadingIcon = { Icon(rememberMail(), contentDescription = null) }
        )
        PasswordField(
            password = password,
            onPasswordChanged = { password = it },
        )
        Spacer(modifier = Modifier.height(6.dp))
        Button(onClick = { login(username, password) }) {
            Text(stringResource(R.string.login))
        }
    }
}