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
fun rememberFormatListBulleted(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "format_list_bulleted",
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
                moveTo(7.375f, 22.125f)
                quadToRelative(-0.875f, 0f, -1.521f, -0.625f)
                quadToRelative(-0.646f, -0.625f, -0.646f, -1.542f)
                quadToRelative(0f, -0.875f, 0.646f, -1.5f)
                reflectiveQuadToRelative(1.521f, -0.625f)
                quadToRelative(0.875f, 0f, 1.5f, 0.646f)
                reflectiveQuadToRelative(0.625f, 1.479f)
                quadToRelative(0f, 0.917f, -0.625f, 1.542f)
                reflectiveQuadToRelative(-1.5f, 0.625f)
                close()
                moveToRelative(0f, -10.167f)
                quadToRelative(-0.917f, 0f, -1.542f, -0.625f)
                reflectiveQuadToRelative(-0.625f, -1.541f)
                quadToRelative(0f, -0.875f, 0.625f, -1.5f)
                reflectiveQuadToRelative(1.542f, -0.625f)
                quadToRelative(0.875f, 0f, 1.5f, 0.646f)
                quadToRelative(0.625f, 0.645f, 0.625f, 1.52f)
                reflectiveQuadToRelative(-0.625f, 1.5f)
                quadToRelative(-0.625f, 0.625f, -1.5f, 0.625f)
                close()
                moveToRelative(0f, 20.334f)
                quadToRelative(-0.875f, 0f, -1.521f, -0.625f)
                quadToRelative(-0.646f, -0.625f, -0.646f, -1.5f)
                reflectiveQuadToRelative(0.646f, -1.521f)
                quadTo(6.5f, 28f, 7.375f, 28f)
                quadToRelative(0.875f, 0f, 1.5f, 0.646f)
                reflectiveQuadToRelative(0.625f, 1.521f)
                quadToRelative(0f, 0.916f, -0.625f, 1.521f)
                quadToRelative(-0.625f, 0.604f, -1.5f, 0.604f)
                close()
                moveToRelative(7.417f, -0.834f)
                quadToRelative(-0.542f, 0f, -0.938f, -0.375f)
                quadToRelative(-0.396f, -0.375f, -0.396f, -0.916f)
                quadToRelative(0f, -0.584f, 0.396f, -0.959f)
                reflectiveQuadToRelative(0.938f, -0.375f)
                horizontalLineTo(33.5f)
                quadToRelative(0.542f, 0f, 0.917f, 0.396f)
                reflectiveQuadToRelative(0.375f, 0.938f)
                quadToRelative(0f, 0.541f, -0.375f, 0.916f)
                reflectiveQuadToRelative(-0.917f, 0.375f)
                close()
                moveToRelative(0f, -10.166f)
                quadToRelative(-0.542f, 0f, -0.938f, -0.375f)
                quadToRelative(-0.396f, -0.375f, -0.396f, -0.917f)
                quadToRelative(0f, -0.583f, 0.396f, -0.958f)
                reflectiveQuadToRelative(0.938f, -0.375f)
                horizontalLineTo(33.5f)
                quadToRelative(0.542f, 0f, 0.917f, 0.395f)
                quadToRelative(0.375f, 0.396f, 0.375f, 0.938f)
                quadToRelative(0f, 0.542f, -0.375f, 0.917f)
                reflectiveQuadToRelative(-0.917f, 0.375f)
                close()
                moveToRelative(0f, -10.167f)
                quadToRelative(-0.542f, 0f, -0.938f, -0.375f)
                quadToRelative(-0.396f, -0.375f, -0.396f, -0.917f)
                quadToRelative(0f, -0.583f, 0.396f, -0.958f)
                reflectiveQuadToRelative(0.938f, -0.375f)
                horizontalLineTo(33.5f)
                quadToRelative(0.542f, 0f, 0.917f, 0.396f)
                reflectiveQuadToRelative(0.375f, 0.937f)
                quadToRelative(0f, 0.542f, -0.375f, 0.917f)
                reflectiveQuadToRelative(-0.917f, 0.375f)
                close()
            }
        }.build()
    }
}