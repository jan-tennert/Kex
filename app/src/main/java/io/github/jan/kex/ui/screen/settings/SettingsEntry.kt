package io.github.jan.kex.ui.screen.settings

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.jan.kex.R
import io.github.jan.kex.ui.icons.rememberInfoIcon
import io.github.jan.kex.ui.icons.rememberRobotIcon
import io.github.jan.kex.ui.icons.rememberSchoolIcon

enum class SettingsEntry(@StringRes val title: Int, val icon: @Composable () -> ImageVector) {
    SchoolCredentials(R.string.settings_school_credentials, { rememberSchoolIcon() }),
    OpenAiCredentials(R.string.settings_openai, { rememberRobotIcon() }),
    Information(R.string.information, { rememberInfoIcon() });
}