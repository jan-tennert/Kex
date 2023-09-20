package io.github.jan.kex.ui.screen.settings.entries

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import io.github.jan.kex.ui.screen.settings.KexTheme
import io.github.jan.kex.ui.screen.settings.LocalKexTheme
import io.github.jan.kex.vm.SettingsViewModel

@Composable
fun ThemeEntry(settingsVm: SettingsViewModel) {
    val themes = remember { KexTheme.entries }
    val currentTheme = LocalKexTheme.current
    Column {
        themes.forEach { theme ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = currentTheme == theme, onClick = { settingsVm.setTheme(theme) })
                //   Icon(theme.icon(), contentDescription = null)
                Text(stringResource(theme.title))
            }
        }
    }
}