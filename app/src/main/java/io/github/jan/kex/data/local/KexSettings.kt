package io.github.jan.kex.data.local

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getStringFlow
import io.github.jan.kex.ui.screen.settings.KexTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface KexSettings {

    val theme: Flow<KexTheme>

    suspend fun setTheme(theme: KexTheme)

}

internal class KexSettingsImpl(
    private val settings: ObservableSettings
): KexSettings {

    @OptIn(ExperimentalSettingsApi::class)
    override val theme: Flow<KexTheme> = settings.getStringFlow(THEME_KEY, KexTheme.SYSTEM.name).map { KexTheme.valueOf(it) }

    override suspend fun setTheme(theme: KexTheme) {
        settings.putString(THEME_KEY, theme.name)
    }

    companion object {
        const val THEME_KEY = "theme"
    }

}