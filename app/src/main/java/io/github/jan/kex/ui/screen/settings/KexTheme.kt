package io.github.jan.kex.ui.screen.settings

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.jan.kex.R
import io.github.jan.kex.ui.icons.rememberDarkModeIcon
import io.github.jan.kex.ui.icons.rememberLightModeIcon
import io.github.jan.kex.ui.icons.rememberPhoneAndroidIcon

enum class KexTheme(@StringRes val title: Int, val icon: @Composable () -> ImageVector) {
    LIGHT(R.string.light, { rememberLightModeIcon() }), DARK(R.string.dark, { rememberDarkModeIcon() }), SYSTEM(R.string.system, { rememberPhoneAndroidIcon() })
}

val LocalKexTheme = compositionLocalOf {
    KexTheme.SYSTEM
}