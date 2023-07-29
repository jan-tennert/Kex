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
fun rememberCircle(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "circle",
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
                moveTo(20f, 36.375f)
                quadToRelative(-3.375f, 0f, -6.375f, -1.292f)
                quadToRelative(-3f, -1.291f, -5.208f, -3.521f)
                quadToRelative(-2.209f, -2.229f, -3.5f, -5.208f)
                quadTo(3.625f, 23.375f, 3.625f, 20f)
                quadToRelative(0f, -3.417f, 1.292f, -6.396f)
                quadToRelative(1.291f, -2.979f, 3.521f, -5.208f)
                quadToRelative(2.229f, -2.229f, 5.208f, -3.5f)
                reflectiveQuadTo(20f, 3.625f)
                quadToRelative(3.417f, 0f, 6.396f, 1.292f)
                quadToRelative(2.979f, 1.291f, 5.208f, 3.5f)
                quadToRelative(2.229f, 2.208f, 3.5f, 5.187f)
                reflectiveQuadTo(36.375f, 20f)
                quadToRelative(0f, 3.375f, -1.292f, 6.375f)
                quadToRelative(-1.291f, 3f, -3.5f, 5.208f)
                quadToRelative(-2.208f, 2.209f, -5.187f, 3.5f)
                quadToRelative(-2.979f, 1.292f, -6.396f, 1.292f)
                close()
                moveToRelative(0f, -2.625f)
                quadToRelative(5.75f, 0f, 9.75f, -4.021f)
                reflectiveQuadToRelative(4f, -9.729f)
                quadToRelative(0f, -5.75f, -4f, -9.75f)
                reflectiveQuadToRelative(-9.75f, -4f)
                quadToRelative(-5.708f, 0f, -9.729f, 4f)
                quadToRelative(-4.021f, 4f, -4.021f, 9.75f)
                quadToRelative(0f, 5.708f, 4.021f, 9.729f)
                quadTo(14.292f, 33.75f, 20f, 33.75f)
                close()
                moveTo(20f, 20f)
                close()
            }
        }.build()
    }
}