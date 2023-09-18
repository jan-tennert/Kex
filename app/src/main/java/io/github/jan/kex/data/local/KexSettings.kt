package io.github.jan.kex.data.local

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getBooleanFlow
import com.russhwolf.settings.coroutines.getStringFlow
import io.github.jan.kex.ui.screen.settings.KexTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface KexSettings {

    val theme: Flow<KexTheme>
    val showedNotificationDialog: Flow<Boolean>

    suspend fun setTheme(theme: KexTheme)

    suspend fun setShowedNotificationDialog(showed: Boolean)

}

@OptIn(ExperimentalSettingsApi::class)
internal class KexSettingsImpl(
    private val settings: ObservableSettings
): KexSettings {

    override val theme: Flow<KexTheme> = settings.getStringFlow(THEME_KEY, KexTheme.SYSTEM.name).map { KexTheme.valueOf(it) }

    override val showedNotificationDialog: Flow<Boolean> = settings.getBooleanFlow(SHOWED_NOTIFICATION_DIALOG_KEY, false)

    override suspend fun setTheme(theme: KexTheme) {
        settings.putString(THEME_KEY, theme.name)
    }

    override suspend fun setShowedNotificationDialog(showed: Boolean) {
        settings.putBoolean(SHOWED_NOTIFICATION_DIALOG_KEY, showed)
    }

    companion object {
        const val THEME_KEY = "theme"
        const val SHOWED_NOTIFICATION_DIALOG_KEY = "showedNotificationDialog"
    }

}