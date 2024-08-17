package io.github.jan.kex.ui.screen.settings.entries

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.jan.kex.R
import io.github.jan.kex.ui.icons.rememberMail
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.compose.auth.ui.annotations.AuthUiExperimental
import io.github.jan.supabase.compose.auth.ui.password.OutlinedPasswordField

@OptIn(SupabaseExperimental::class, ExperimentalMaterial3Api::class, AuthUiExperimental::class)
@Composable
fun SchoolEntry(username: String, password: String, onUsernameChange: (String) -> Unit, onPasswordChange: (String) -> Unit) {
    Column {
        Text(stringResource(R.string.lower_grades))
        OutlinedTextField(
            value = username,
            onValueChange = onUsernameChange,
            label = { Text(text = stringResource(R.string.username)) },
            leadingIcon = { Icon(rememberMail(), contentDescription = null) },
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedPasswordField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text(text = stringResource(R.string.password)) },
            mandatory = false,
        )
    }
}