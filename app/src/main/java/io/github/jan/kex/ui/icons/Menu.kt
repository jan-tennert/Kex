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
fun rememberMenu(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "menu",
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
                moveTo(6.542f, 29.833f)
                quadToRelative(-0.542f, 0f, -0.938f, -0.395f)
                quadToRelative(-0.396f, -0.396f, -0.396f, -0.938f)
                quadToRelative(0f, -0.542f, 0.396f, -0.917f)
                reflectiveQuadToRelative(0.938f, -0.375f)
                horizontalLineTo(33.5f)
                quadToRelative(0.542f, 0f, 0.917f, 0.375f)
                reflectiveQuadToRelative(0.375f, 0.917f)
                quadToRelative(0f, 0.583f, -0.375f, 0.958f)
                reflectiveQuadToRelative(-0.917f, 0.375f)
                close()
                moveToRelative(0f, -8.541f)
                quadToRelative(-0.542f, 0f, -0.938f, -0.375f)
                quadToRelative(-0.396f, -0.375f, -0.396f, -0.959f)
                quadToRelative(0f, -0.541f, 0.396f, -0.916f)
                reflectiveQuadToRelative(0.938f, -0.375f)
                horizontalLineTo(33.5f)
                quadToRelative(0.542f, 0f, 0.917f, 0.395f)
                quadToRelative(0.375f, 0.396f, 0.375f, 0.938f)
                quadToRelative(0f, 0.542f, -0.375f, 0.917f)
                reflectiveQuadToRelative(-0.917f, 0.375f)
                close()
                moveToRelative(0f, -8.5f)
                quadToRelative(-0.542f, 0f, -0.938f, -0.375f)
                quadToRelative(-0.396f, -0.375f, -0.396f, -0.959f)
                quadToRelative(0f, -0.541f, 0.396f, -0.916f)
                reflectiveQuadToRelative(0.938f, -0.375f)
                horizontalLineTo(33.5f)
                quadToRelative(0.542f, 0f, 0.917f, 0.396f)
                quadToRelative(0.375f, 0.395f, 0.375f, 0.937f)
                reflectiveQuadToRelative(-0.375f, 0.917f)
                quadToRelative(-0.375f, 0.375f, -0.917f, 0.375f)
                close()
            }
        }.build()
    }
}