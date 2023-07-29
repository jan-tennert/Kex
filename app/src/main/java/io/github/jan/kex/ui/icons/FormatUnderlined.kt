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
fun rememberFormatUnderlined(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "format_underlined",
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
                moveTo(9.75f, 34.917f)
                quadToRelative(-0.583f, 0f, -0.958f, -0.396f)
                reflectiveQuadToRelative(-0.375f, -0.938f)
                quadToRelative(0f, -0.541f, 0.375f, -0.916f)
                reflectiveQuadToRelative(0.958f, -0.375f)
                horizontalLineToRelative(20.542f)
                quadToRelative(0.5f, 0f, 0.896f, 0.375f)
                quadToRelative(0.395f, 0.375f, 0.395f, 0.916f)
                quadToRelative(0f, 0.584f, -0.395f, 0.959f)
                quadToRelative(-0.396f, 0.375f, -0.896f, 0.375f)
                close()
                moveTo(20f, 28.25f)
                quadToRelative(-4.208f, 0f, -7.062f, -2.833f)
                quadToRelative(-2.855f, -2.834f, -2.855f, -7.042f)
                verticalLineTo(6.792f)
                quadToRelative(0f, -0.709f, 0.5f, -1.209f)
                quadToRelative(0.5f, -0.5f, 1.209f, -0.5f)
                quadToRelative(0.708f, 0f, 1.229f, 0.5f)
                quadToRelative(0.521f, 0.5f, 0.521f, 1.209f)
                verticalLineToRelative(11.666f)
                quadToRelative(0f, 2.709f, 1.833f, 4.521f)
                quadToRelative(1.833f, 1.813f, 4.625f, 1.813f)
                reflectiveQuadToRelative(4.625f, -1.813f)
                quadToRelative(1.833f, -1.812f, 1.833f, -4.521f)
                verticalLineTo(6.792f)
                quadToRelative(0f, -0.709f, 0.521f, -1.209f)
                quadToRelative(0.521f, -0.5f, 1.229f, -0.5f)
                quadToRelative(0.709f, 0f, 1.209f, 0.5f)
                quadToRelative(0.5f, 0.5f, 0.5f, 1.209f)
                verticalLineToRelative(11.583f)
                quadToRelative(0f, 4.208f, -2.855f, 7.042f)
                quadTo(24.208f, 28.25f, 20f, 28.25f)
                close()
            }
        }.build()
    }
}