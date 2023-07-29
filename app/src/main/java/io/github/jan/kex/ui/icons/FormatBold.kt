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
fun rememberFormatBold(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "format_bold",
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
                moveTo(14.458f, 29.625f)
                quadToRelative(-1.125f, 0f, -1.896f, -0.771f)
                quadToRelative(-0.77f, -0.771f, -0.77f, -1.896f)
                verticalLineTo(9.125f)
                quadToRelative(0f, -1.083f, 0.77f, -1.854f)
                quadToRelative(0.771f, -0.771f, 1.896f, -0.771f)
                horizontalLineToRelative(6.584f)
                quadToRelative(2.875f, 0f, 4.833f, 1.688f)
                quadToRelative(1.958f, 1.687f, 1.958f, 4.395f)
                quadToRelative(0f, 1.792f, -0.854f, 3.042f)
                quadToRelative(-0.854f, 1.25f, -2.354f, 1.917f)
                verticalLineToRelative(0.25f)
                quadToRelative(1.875f, 0.541f, 2.937f, 1.937f)
                quadToRelative(1.063f, 1.396f, 1.063f, 3.479f)
                quadToRelative(0f, 2.917f, -2.083f, 4.667f)
                quadToRelative(-2.084f, 1.75f, -5.25f, 1.75f)
                close()
                moveToRelative(1f, -13.417f)
                horizontalLineToRelative(5.292f)
                quadToRelative(1.542f, 0f, 2.542f, -0.895f)
                quadToRelative(1f, -0.896f, 1f, -2.355f)
                quadToRelative(0f, -1.458f, -1f, -2.375f)
                quadToRelative(-1f, -0.916f, -2.542f, -0.916f)
                horizontalLineToRelative(-5.292f)
                close()
                moveToRelative(0f, 10.167f)
                horizontalLineToRelative(5.625f)
                quadToRelative(1.792f, 0f, 2.834f, -0.958f)
                quadToRelative(1.041f, -0.959f, 1.041f, -2.584f)
                reflectiveQuadToRelative(-1.041f, -2.583f)
                quadToRelative(-1.042f, -0.958f, -2.834f, -0.958f)
                horizontalLineToRelative(-5.625f)
                close()
            }
        }.build()
    }
}