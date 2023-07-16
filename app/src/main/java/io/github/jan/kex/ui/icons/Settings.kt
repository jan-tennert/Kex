package io.github.jan.kex.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val SettingsIcon: ImageVector
    get() {
        if(_settingsIcon != null) {
            return _settingsIcon!!
        }
        _settingsIcon = ImageVector.Builder(
            name = "settings",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(16.083f, 36.375f)
                lineToRelative(-0.791f, -5.167f)
                quadToRelative(-0.375f, -0.125f, -1.167f, -0.583f)
                quadToRelative(-0.792f, -0.458f, -1.708f, -1.042f)
                lineToRelative(-4.834f, 2.125f)
                lineToRelative(-4f, -6.958f)
                lineTo(8f, 21.5f)
                quadToRelative(-0.083f, -0.333f, -0.104f, -0.708f)
                quadToRelative(-0.021f, -0.375f, -0.021f, -0.792f)
                quadToRelative(0f, -0.333f, 0.021f, -0.75f)
                reflectiveQuadTo(8f, 18.417f)
                lineToRelative(-4.417f, -3.209f)
                lineToRelative(4f, -6.916f)
                lineToRelative(4.875f, 2.166f)
                quadTo(13f, 10f, 13.771f, 9.542f)
                quadToRelative(0.771f, -0.459f, 1.521f, -0.709f)
                lineToRelative(0.791f, -5.25f)
                horizontalLineToRelative(7.834f)
                lineToRelative(0.791f, 5.209f)
                quadToRelative(0.709f, 0.25f, 1.48f, 0.687f)
                quadToRelative(0.77f, 0.438f, 1.354f, 0.979f)
                lineToRelative(4.916f, -2.166f)
                lineToRelative(3.959f, 6.916f)
                lineToRelative(-4.459f, 3.209f)
                quadToRelative(0.042f, 0.375f, 0.063f, 0.771f)
                quadToRelative(0.021f, 0.395f, 0.021f, 0.812f)
                quadToRelative(0f, 0.417f, -0.021f, 0.812f)
                quadToRelative(-0.021f, 0.396f, -0.063f, 0.73f)
                lineToRelative(4.417f, 3.208f)
                lineToRelative(-3.958f, 6.958f)
                lineToRelative(-4.875f, -2.166f)
                quadToRelative(-0.542f, 0.458f, -1.271f, 0.896f)
                quadToRelative(-0.729f, 0.437f, -1.563f, 0.77f)
                lineToRelative(-0.791f, 5.167f)
                close()
                moveToRelative(3.875f, -10.958f)
                quadToRelative(2.25f, 0f, 3.834f, -1.584f)
                quadTo(25.375f, 22.25f, 25.375f, 20f)
                reflectiveQuadToRelative(-1.583f, -3.833f)
                quadToRelative(-1.584f, -1.584f, -3.834f, -1.584f)
                reflectiveQuadToRelative(-3.833f, 1.584f)
                quadTo(14.542f, 17.75f, 14.542f, 20f)
                reflectiveQuadToRelative(1.583f, 3.833f)
                quadToRelative(1.583f, 1.584f, 3.833f, 1.584f)
                close()
                moveToRelative(0f, -2.625f)
                quadToRelative(-1.166f, 0f, -1.979f, -0.813f)
                quadToRelative(-0.812f, -0.812f, -0.812f, -1.979f)
                reflectiveQuadToRelative(0.812f, -1.979f)
                quadToRelative(0.813f, -0.813f, 1.979f, -0.813f)
                quadToRelative(1.167f, 0f, 1.98f, 0.813f)
                quadToRelative(0.812f, 0.812f, 0.812f, 1.979f)
                reflectiveQuadToRelative(-0.812f, 1.979f)
                quadToRelative(-0.813f, 0.813f, -1.98f, 0.813f)
                close()
                moveTo(20f, 19.958f)
                close()
                moveTo(18.25f, 33.75f)
                horizontalLineToRelative(3.5f)
                lineToRelative(0.583f, -4.583f)
                quadToRelative(1.334f, -0.375f, 2.479f, -1.021f)
                quadToRelative(1.146f, -0.646f, 2.105f, -1.646f)
                lineToRelative(4.333f, 1.875f)
                lineToRelative(1.625f, -2.917f)
                lineToRelative(-3.833f, -2.791f)
                quadToRelative(0.166f, -0.667f, 0.27f, -1.334f)
                quadToRelative(0.105f, -0.666f, 0.105f, -1.333f)
                quadToRelative(0f, -0.708f, -0.084f, -1.333f)
                quadToRelative(-0.083f, -0.625f, -0.291f, -1.334f)
                lineToRelative(3.833f, -2.833f)
                lineToRelative(-1.583f, -2.917f)
                lineToRelative(-4.375f, 1.875f)
                quadTo(26f, 12.5f, 24.833f, 11.792f)
                quadToRelative(-1.166f, -0.709f, -2.5f, -0.959f)
                lineToRelative(-0.541f, -4.625f)
                horizontalLineTo(18.25f)
                lineToRelative(-0.583f, 4.625f)
                quadToRelative(-1.417f, 0.334f, -2.521f, 0.959f)
                quadToRelative(-1.104f, 0.625f, -2.104f, 1.666f)
                lineTo(8.75f, 11.583f)
                lineTo(7.083f, 14.5f)
                lineToRelative(3.792f, 2.792f)
                quadToRelative(-0.167f, 0.708f, -0.271f, 1.354f)
                quadToRelative(-0.104f, 0.646f, -0.104f, 1.312f)
                quadToRelative(0f, 0.709f, 0.104f, 1.354f)
                quadToRelative(0.104f, 0.646f, 0.271f, 1.355f)
                lineToRelative(-3.792f, 2.791f)
                lineToRelative(1.667f, 2.917f)
                lineToRelative(4.292f, -1.833f)
                quadToRelative(1f, 1f, 2.146f, 1.646f)
                quadToRelative(1.145f, 0.645f, 2.479f, 0.979f)
                close()
            }
        }.build()
        return _settingsIcon!!
    }
private var _settingsIcon: ImageVector? = null