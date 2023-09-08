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
fun rememberLightModeIcon(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "light_mode",
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
                moveTo(20f, 25.625f)
                quadToRelative(2.333f, 0f, 3.979f, -1.646f)
                reflectiveQuadTo(25.625f, 20f)
                quadToRelative(0f, -2.333f, -1.646f, -3.979f)
                reflectiveQuadTo(20f, 14.375f)
                quadToRelative(-2.333f, 0f, -3.979f, 1.646f)
                reflectiveQuadTo(14.375f, 20f)
                quadToRelative(0f, 2.333f, 1.646f, 3.979f)
                reflectiveQuadTo(20f, 25.625f)
                close()
                moveToRelative(0f, 2.625f)
                quadToRelative(-3.417f, 0f, -5.833f, -2.417f)
                quadTo(11.75f, 23.417f, 11.75f, 20f)
                quadToRelative(0f, -3.417f, 2.417f, -5.854f)
                quadToRelative(2.416f, -2.438f, 5.833f, -2.438f)
                quadToRelative(3.417f, 0f, 5.854f, 2.438f)
                quadToRelative(2.438f, 2.437f, 2.438f, 5.854f)
                quadToRelative(0f, 3.417f, -2.438f, 5.833f)
                quadTo(23.417f, 28.25f, 20f, 28.25f)
                close()
                moveTo(3.042f, 21.292f)
                quadToRelative(-0.542f, 0f, -0.917f, -0.375f)
                reflectiveQuadTo(1.75f, 20f)
                quadToRelative(0f, -0.542f, 0.375f, -0.938f)
                quadToRelative(0.375f, -0.395f, 0.917f, -0.395f)
                horizontalLineToRelative(3.916f)
                quadToRelative(0.542f, 0f, 0.938f, 0.395f)
                quadToRelative(0.396f, 0.396f, 0.396f, 0.938f)
                quadToRelative(0f, 0.542f, -0.396f, 0.917f)
                reflectiveQuadToRelative(-0.938f, 0.375f)
                close()
                moveToRelative(30f, 0f)
                quadToRelative(-0.542f, 0f, -0.917f, -0.375f)
                reflectiveQuadTo(31.75f, 20f)
                quadToRelative(0f, -0.542f, 0.375f, -0.938f)
                quadToRelative(0.375f, -0.395f, 0.917f, -0.395f)
                horizontalLineToRelative(3.916f)
                quadToRelative(0.542f, 0f, 0.938f, 0.395f)
                quadToRelative(0.396f, 0.396f, 0.396f, 0.938f)
                quadToRelative(0f, 0.542f, -0.396f, 0.917f)
                reflectiveQuadToRelative(-0.938f, 0.375f)
                close()
                moveTo(20f, 8.25f)
                quadToRelative(-0.542f, 0f, -0.917f, -0.375f)
                reflectiveQuadToRelative(-0.375f, -0.917f)
                verticalLineTo(3.042f)
                quadToRelative(0f, -0.542f, 0.375f, -0.938f)
                quadToRelative(0.375f, -0.396f, 0.917f, -0.396f)
                reflectiveQuadToRelative(0.938f, 0.396f)
                quadToRelative(0.395f, 0.396f, 0.395f, 0.938f)
                verticalLineToRelative(3.916f)
                quadToRelative(0f, 0.542f, -0.395f, 0.917f)
                quadToRelative(-0.396f, 0.375f, -0.938f, 0.375f)
                close()
                moveToRelative(0f, 30f)
                quadToRelative(-0.542f, 0f, -0.917f, -0.375f)
                reflectiveQuadToRelative(-0.375f, -0.917f)
                verticalLineToRelative(-3.916f)
                quadToRelative(0f, -0.542f, 0.375f, -0.938f)
                quadToRelative(0.375f, -0.396f, 0.917f, -0.396f)
                reflectiveQuadToRelative(0.938f, 0.396f)
                quadToRelative(0.395f, 0.396f, 0.395f, 0.938f)
                verticalLineToRelative(3.916f)
                quadToRelative(0f, 0.542f, -0.395f, 0.917f)
                quadToRelative(-0.396f, 0.375f, -0.938f, 0.375f)
                close()
                moveTo(9.875f, 11.708f)
                lineTo(7.667f, 9.542f)
                quadToRelative(-0.417f, -0.375f, -0.396f, -0.917f)
                quadToRelative(0.021f, -0.542f, 0.396f, -0.958f)
                quadToRelative(0.416f, -0.375f, 0.958f, -0.396f)
                quadToRelative(0.542f, -0.021f, 0.917f, 0.396f)
                lineToRelative(2.166f, 2.166f)
                quadToRelative(0.375f, 0.417f, 0.375f, 0.938f)
                quadToRelative(0f, 0.521f, -0.375f, 0.937f)
                quadToRelative(-0.375f, 0.375f, -0.916f, 0.355f)
                quadToRelative(-0.542f, -0.021f, -0.917f, -0.355f)
                close()
                moveToRelative(20.583f, 20.625f)
                lineToRelative(-2.166f, -2.208f)
                quadToRelative(-0.334f, -0.375f, -0.354f, -0.917f)
                quadToRelative(-0.021f, -0.541f, 0.395f, -0.916f)
                quadToRelative(0.334f, -0.417f, 0.875f, -0.396f)
                quadToRelative(0.542f, 0.021f, 0.959f, 0.396f)
                lineToRelative(2.166f, 2.166f)
                quadToRelative(0.417f, 0.375f, 0.396f, 0.917f)
                quadToRelative(-0.021f, 0.542f, -0.396f, 0.958f)
                quadToRelative(-0.416f, 0.375f, -0.958f, 0.396f)
                quadToRelative(-0.542f, 0.021f, -0.917f, -0.396f)
                close()
                moveToRelative(-2.166f, -20.625f)
                quadToRelative(-0.417f, -0.375f, -0.396f, -0.916f)
                quadToRelative(0.021f, -0.542f, 0.396f, -0.959f)
                lineToRelative(2.166f, -2.166f)
                quadToRelative(0.375f, -0.417f, 0.917f, -0.396f)
                quadToRelative(0.542f, 0.021f, 0.958f, 0.396f)
                quadToRelative(0.375f, 0.416f, 0.396f, 0.958f)
                quadToRelative(0.021f, 0.542f, -0.396f, 0.917f)
                lineToRelative(-2.166f, 2.166f)
                quadToRelative(-0.375f, 0.375f, -0.917f, 0.375f)
                reflectiveQuadToRelative(-0.958f, -0.375f)
                close()
                moveTo(7.667f, 32.333f)
                quadToRelative(-0.375f, -0.416f, -0.396f, -0.958f)
                quadToRelative(-0.021f, -0.542f, 0.396f, -0.917f)
                lineToRelative(2.208f, -2.166f)
                quadToRelative(0.375f, -0.375f, 0.917f, -0.375f)
                quadToRelative(0.541f, 0f, 0.916f, 0.375f)
                quadToRelative(0.417f, 0.375f, 0.396f, 0.916f)
                quadToRelative(-0.021f, 0.542f, -0.396f, 0.917f)
                lineToRelative(-2.166f, 2.208f)
                quadToRelative(-0.375f, 0.417f, -0.917f, 0.396f)
                quadToRelative(-0.542f, -0.021f, -0.958f, -0.396f)
                close()
                moveTo(20f, 20f)
                close()
            }
        }.build()
    }
}