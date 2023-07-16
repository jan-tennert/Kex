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
fun rememberMail(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "mail",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.dp,
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
                moveTo(6.25f, 33.083f)
                quadToRelative(-1.083f, 0f, -1.854f, -0.791f)
                quadToRelative(-0.771f, -0.792f, -0.771f, -1.834f)
                verticalLineTo(9.542f)
                quadToRelative(0f, -1.042f, 0.771f, -1.834f)
                quadToRelative(0.771f, -0.791f, 1.854f, -0.791f)
                horizontalLineToRelative(27.5f)
                quadToRelative(1.083f, 0f, 1.854f, 0.791f)
                quadToRelative(0.771f, 0.792f, 0.771f, 1.834f)
                verticalLineToRelative(20.916f)
                quadToRelative(0f, 1.042f, -0.771f, 1.834f)
                quadToRelative(-0.771f, 0.791f, -1.854f, 0.791f)
                close()
                moveToRelative(27.5f, -21.041f)
                lineTo(20.708f, 20.5f)
                quadToRelative(-0.208f, 0.083f, -0.354f, 0.146f)
                quadToRelative(-0.146f, 0.062f, -0.354f, 0.062f)
                reflectiveQuadToRelative(-0.354f, -0.062f)
                quadToRelative(-0.146f, -0.063f, -0.313f, -0.146f)
                lineTo(6.25f, 12.042f)
                verticalLineToRelative(18.416f)
                horizontalLineToRelative(27.5f)
                close()
                moveTo(20f, 18.292f)
                lineToRelative(13.625f, -8.75f)
                horizontalLineTo(6.417f)
                close()
                moveToRelative(-13.75f, -6.25f)
                verticalLineToRelative(0.333f)
                verticalLineToRelative(-1.812f)
                verticalLineToRelative(0.02f)
                verticalLineToRelative(-1.041f)
                verticalLineToRelative(1.041f)
                verticalLineToRelative(-0.021f)
                verticalLineTo(12.375f)
                verticalLineToRelative(-0.333f)
                verticalLineToRelative(18.416f)
                close()
            }
        }.build()
    }
}