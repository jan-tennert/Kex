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
fun rememberFormatItalic(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "format_italic",
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
                moveTo(10.5f, 33.208f)
                quadToRelative(-0.875f, 0f, -1.479f, -0.604f)
                quadToRelative(-0.604f, -0.604f, -0.604f, -1.521f)
                quadToRelative(0f, -0.875f, 0.604f, -1.479f)
                quadTo(9.625f, 29f, 10.5f, 29f)
                horizontalLineToRelative(3.583f)
                lineToRelative(7.25f, -18f)
                horizontalLineTo(17.25f)
                quadToRelative(-0.875f, 0f, -1.479f, -0.625f)
                quadToRelative(-0.604f, -0.625f, -0.604f, -1.5f)
                reflectiveQuadToRelative(0.604f, -1.479f)
                quadToRelative(0.604f, -0.604f, 1.479f, -0.604f)
                horizontalLineTo(29.5f)
                quadToRelative(0.875f, 0f, 1.5f, 0.604f)
                reflectiveQuadToRelative(0.625f, 1.521f)
                quadToRelative(0f, 0.875f, -0.625f, 1.479f)
                quadToRelative(-0.625f, 0.604f, -1.5f, 0.604f)
                horizontalLineToRelative(-3.583f)
                lineToRelative(-7.292f, 18f)
                horizontalLineToRelative(4.083f)
                quadToRelative(0.875f, 0f, 1.5f, 0.625f)
                reflectiveQuadToRelative(0.625f, 1.5f)
                quadToRelative(0f, 0.875f, -0.625f, 1.479f)
                quadToRelative(-0.625f, 0.604f, -1.5f, 0.604f)
                close()
            }
        }.build()
    }
}