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
fun rememberCode(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "code",
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
                moveTo(27.5f, 28.917f)
                quadToRelative(-0.417f, 0.375f, -0.938f, 0.354f)
                quadToRelative(-0.52f, -0.021f, -0.937f, -0.396f)
                quadToRelative(-0.417f, -0.417f, -0.417f, -0.958f)
                quadToRelative(0f, -0.542f, 0.417f, -0.959f)
                lineToRelative(7.042f, -7.041f)
                lineToRelative(-7f, -7f)
                quadToRelative(-0.417f, -0.375f, -0.396f, -0.938f)
                quadToRelative(0.021f, -0.562f, 0.437f, -0.937f)
                quadToRelative(0.375f, -0.417f, 0.917f, -0.417f)
                reflectiveQuadToRelative(0.917f, 0.417f)
                lineToRelative(8f, 8f)
                quadToRelative(0.375f, 0.375f, 0.375f, 0.916f)
                quadToRelative(0f, 0.542f, -0.375f, 0.917f)
                close()
                moveToRelative(-15f, -0.125f)
                lineToRelative(-8f, -7.959f)
                quadToRelative(-0.417f, -0.375f, -0.417f, -0.895f)
                quadToRelative(0f, -0.521f, 0.417f, -0.938f)
                lineToRelative(8.042f, -8.042f)
                quadToRelative(0.375f, -0.375f, 0.916f, -0.375f)
                quadToRelative(0.542f, 0f, 0.959f, 0.375f)
                quadToRelative(0.416f, 0.417f, 0.416f, 0.98f)
                quadToRelative(0f, 0.562f, -0.416f, 0.937f)
                lineToRelative(-7.084f, 7.083f)
                lineToRelative(7.042f, 7f)
                quadToRelative(0.375f, 0.417f, 0.375f, 0.938f)
                quadToRelative(0f, 0.521f, -0.375f, 0.896f)
                quadToRelative(-0.417f, 0.416f, -0.958f, 0.416f)
                quadToRelative(-0.542f, 0f, -0.917f, -0.416f)
                close()
            }
        }.build()
    }
}