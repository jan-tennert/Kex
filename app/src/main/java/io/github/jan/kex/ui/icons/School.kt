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
fun rememberSchoolIcon(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "school",
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
                moveTo(19.958f, 34.208f)
                quadToRelative(-0.333f, 0f, -0.646f, -0.083f)
                quadToRelative(-0.312f, -0.083f, -0.604f, -0.25f)
                lineToRelative(-9.083f, -4.958f)
                quadToRelative(-0.667f, -0.334f, -1.021f, -0.959f)
                quadToRelative(-0.354f, -0.625f, -0.354f, -1.375f)
                verticalLineToRelative(-8.208f)
                lineToRelative(-4.083f, -2.208f)
                quadToRelative(-0.334f, -0.209f, -0.5f, -0.5f)
                quadTo(3.5f, 15.375f, 3.5f, 15f)
                quadToRelative(0f, -0.333f, 0.167f, -0.646f)
                quadToRelative(0.166f, -0.312f, 0.5f, -0.479f)
                lineToRelative(14.541f, -7.958f)
                quadToRelative(0.292f, -0.167f, 0.625f, -0.25f)
                quadToRelative(0.334f, -0.084f, 0.625f, -0.084f)
                quadToRelative(0.334f, 0f, 0.646f, 0.084f)
                quadToRelative(0.313f, 0.083f, 0.646f, 0.25f)
                lineToRelative(16f, 8.708f)
                quadToRelative(0.333f, 0.208f, 0.521f, 0.5f)
                quadToRelative(0.187f, 0.292f, 0.187f, 0.667f)
                verticalLineToRelative(10.875f)
                quadToRelative(0f, 0.541f, -0.396f, 0.916f)
                quadToRelative(-0.395f, 0.375f, -0.937f, 0.375f)
                reflectiveQuadToRelative(-0.937f, -0.375f)
                quadToRelative(-0.396f, -0.375f, -0.396f, -0.916f)
                verticalLineTo(16.542f)
                lineToRelative(-3.584f, 1.833f)
                verticalLineToRelative(8.208f)
                quadToRelative(0f, 0.75f, -0.375f, 1.375f)
                reflectiveQuadToRelative(-1f, 0.959f)
                lineToRelative(-9.083f, 4.958f)
                quadToRelative(-0.292f, 0.167f, -0.625f, 0.25f)
                quadToRelative(-0.333f, 0.083f, -0.667f, 0.083f)
                close()
                moveToRelative(0f, -12.416f)
                lineTo(32.417f, 15f)
                lineTo(19.958f, 8.292f)
                lineTo(7.583f, 15f)
                close()
                moveToRelative(0f, 9.791f)
                lineToRelative(9.084f, -4.958f)
                verticalLineToRelative(-6.75f)
                lineToRelative(-7.792f, 4.25f)
                quadToRelative(-0.292f, 0.167f, -0.625f, 0.25f)
                quadToRelative(-0.333f, 0.083f, -0.667f, 0.083f)
                quadToRelative(-0.333f, 0f, -0.625f, -0.083f)
                quadToRelative(-0.291f, -0.083f, -0.583f, -0.25f)
                lineToRelative(-7.875f, -4.292f)
                verticalLineToRelative(6.792f)
                close()
                moveToRelative(0.084f, -9.791f)
                close()
                moveToRelative(-0.084f, 3.958f)
                close()
                moveToRelative(0f, 0f)
                close()
            }
        }.build()
    }
}