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
fun rememberDeselect(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "deselect",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
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
                moveTo(5.25f, 21.708f)
                verticalLineToRelative(-3.416f)
                horizontalLineToRelative(2.625f)
                verticalLineToRelative(3.416f)
                close()
                moveToRelative(6.167f, 13.084f)
                verticalLineToRelative(-2.667f)
                horizontalLineToRelative(3.375f)
                verticalLineToRelative(2.667f)
                close()
                moveToRelative(6.916f, -26.917f)
                verticalLineTo(5.25f)
                horizontalLineToRelative(3.375f)
                verticalLineToRelative(2.625f)
                close()
                moveToRelative(13.792f, 0f)
                verticalLineTo(5.25f)
                quadToRelative(1.083f, 0f, 1.875f, 0.771f)
                reflectiveQuadToRelative(0.792f, 1.854f)
                close()
                moveTo(7.875f, 34.792f)
                quadToRelative(-1.042f, 0f, -1.833f, -0.792f)
                quadToRelative(-0.792f, -0.792f, -0.792f, -1.875f)
                horizontalLineToRelative(2.625f)
                close()
                moveTo(5.25f, 28.625f)
                verticalLineToRelative(-3.417f)
                horizontalLineToRelative(2.625f)
                verticalLineToRelative(3.417f)
                close()
                moveToRelative(13.083f, 6.167f)
                verticalLineToRelative(-2.667f)
                horizontalLineToRelative(3.375f)
                verticalLineToRelative(2.667f)
                close()
                moveToRelative(13.792f, -13.084f)
                verticalLineToRelative(-3.416f)
                horizontalLineToRelative(2.667f)
                verticalLineToRelative(3.416f)
                close()
                moveToRelative(0f, -6.916f)
                verticalLineToRelative(-3.375f)
                horizontalLineToRelative(2.667f)
                verticalLineToRelative(3.375f)
                close()
                moveToRelative(-6.917f, -6.917f)
                verticalLineTo(5.25f)
                horizontalLineToRelative(3.417f)
                verticalLineToRelative(2.625f)
                close()
                moveTo(14.792f, 5.25f)
                verticalLineToRelative(2.625f)
                horizontalLineToRelative(-2.917f)
                lineToRelative(-0.458f, -0.458f)
                verticalLineTo(5.25f)
                close()
                moveToRelative(20f, 19.958f)
                verticalLineToRelative(3.417f)
                horizontalLineToRelative(-2.209f)
                lineToRelative(-0.458f, -0.458f)
                verticalLineToRelative(-2.959f)
                close()
                moveToRelative(-20.75f, 0.75f)
                horizontalLineToRelative(8.041f)
                lineToRelative(-8.041f, -8f)
                close()
                moveToRelative(11.166f, 8.834f)
                verticalLineToRelative(-2.667f)
                horizontalLineToRelative(3.417f)
                verticalLineToRelative(2.667f)
                close()
                moveToRelative(3.417f, -9.75f)
                lineToRelative(-2.667f, -2.667f)
                verticalLineToRelative(-8.333f)
                horizontalLineToRelative(-8.333f)
                lineTo(15f, 11.417f)
                horizontalLineToRelative(12.292f)
                quadToRelative(0.583f, 0f, 0.958f, 0.375f)
                reflectiveQuadToRelative(0.375f, 0.916f)
                close()
                moveTo(5.25f, 14.792f)
                verticalLineToRelative(-3.375f)
                horizontalLineToRelative(2.625f)
                verticalLineToRelative(3.375f)
                close()
                moveToRelative(28.5f, 22.791f)
                lineToRelative(-9.042f, -8.958f)
                horizontalLineToRelative(-12f)
                quadToRelative(-0.541f, 0f, -0.916f, -0.375f)
                reflectiveQuadToRelative(-0.375f, -0.958f)
                verticalLineTo(15.333f)
                lineToRelative(-8.709f, -8.75f)
                quadToRelative(-0.416f, -0.375f, -0.416f, -0.916f)
                quadToRelative(0f, -0.542f, 0.375f, -0.959f)
                quadToRelative(0.416f, -0.375f, 0.958f, -0.375f)
                reflectiveQuadToRelative(0.917f, 0.375f)
                lineTo(35.583f, 35.75f)
                quadToRelative(0.375f, 0.375f, 0.375f, 0.917f)
                quadToRelative(0f, 0.541f, -0.375f, 0.958f)
                quadToRelative(-0.416f, 0.375f, -0.937f, 0.375f)
                quadToRelative(-0.521f, 0f, -0.896f, -0.417f)
                close()
            }
        }.build()
    }
}