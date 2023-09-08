package io.github.jan.kex.ui.icons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Composable
fun rememberDarkModeIcon(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "dark_mode",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
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
                moveTo(20f, 34.917f)
                quadToRelative(-6.208f, 0f, -10.562f, -4.355f)
                quadTo(5.083f, 26.208f, 5.083f, 20f)
                quadToRelative(0f, -5.542f, 3.417f, -9.583f)
                quadToRelative(3.417f, -4.042f, 8.833f, -5.042f)
                quadToRelative(1.584f, -0.292f, 2.125f, 0.604f)
                quadToRelative(0.542f, 0.896f, -0.166f, 2.438f)
                quadToRelative(-0.417f, 0.958f, -0.625f, 1.979f)
                quadToRelative(-0.209f, 1.021f, -0.209f, 2.104f)
                quadToRelative(0f, 3.792f, 2.625f, 6.417f)
                reflectiveQuadToRelative(6.417f, 2.625f)
                quadToRelative(1.083f, 0f, 2.083f, -0.209f)
                quadToRelative(1f, -0.208f, 1.959f, -0.583f)
                quadToRelative(1.708f, -0.708f, 2.541f, -0.104f)
                quadToRelative(0.834f, 0.604f, 0.5f, 2.271f)
                quadToRelative(-0.958f, 5.125f, -4.979f, 8.562f)
                quadToRelative(-4.021f, 3.438f, -9.604f, 3.438f)
                close()
                moveToRelative(0f, -2.625f)
                quadToRelative(4.25f, 0f, 7.521f, -2.604f)
                quadToRelative(3.271f, -2.605f, 4.271f, -6.271f)
                quadToRelative(-1f, 0.375f, -2.104f, 0.583f)
                quadToRelative(-1.105f, 0.208f, -2.188f, 0.208f)
                quadToRelative(-4.875f, 0f, -8.292f, -3.416f)
                quadToRelative(-3.416f, -3.417f, -3.416f, -8.292f)
                quadToRelative(0f, -0.958f, 0.187f, -2.021f)
                quadToRelative(0.188f, -1.062f, 0.646f, -2.354f)
                quadToRelative(-3.875f, 1.208f, -6.396f, 4.5f)
                reflectiveQuadTo(7.708f, 20f)
                quadToRelative(0f, 5.125f, 3.584f, 8.708f)
                quadToRelative(3.583f, 3.584f, 8.708f, 3.584f)
                close()
                moveToRelative(-0.208f, -12.084f)
                close()
            }
        }.build()
    }
}