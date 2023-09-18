package io.github.jan.kex.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.kex.data.local.KexSettings
import io.github.jan.kex.ui.screen.settings.KexTheme
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val kexSettings: KexSettings
): ViewModel() {

    val currentTheme = kexSettings.theme.stateIn(viewModelScope, SharingStarted.Eagerly, KexTheme.SYSTEM)
    val showedNotificationDialog = kexSettings.showedNotificationDialog.stateIn(viewModelScope, SharingStarted.Eagerly, false).stateIn(viewModelScope, SharingStarted.Eagerly, true)

    fun setTheme(theme: KexTheme) {
        viewModelScope.launch {
            kexSettings.setTheme(theme)
        }
    }

    fun setShowedNotificationDialog(showed: Boolean) {
        viewModelScope.launch {
            kexSettings.setShowedNotificationDialog(showed)
        }
    }

}