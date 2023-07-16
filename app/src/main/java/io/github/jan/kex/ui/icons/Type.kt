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
fun rememberTypeSpecimen(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "type_specimen",
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
                moveTo(19.917f, 19.083f)
                lineToRelative(2.666f, -7f)
                horizontalLineToRelative(0.167f)
                lineToRelative(2.625f, 7f)
                close()
                moveToRelative(-8.375f, 12.042f)
                quadToRelative(-1.084f, 0f, -1.875f, -0.792f)
                quadToRelative(-0.792f, -0.791f, -0.792f, -1.875f)
                verticalLineTo(6.25f)
                quadToRelative(0f, -1.083f, 0.792f, -1.854f)
                quadToRelative(0.791f, -0.771f, 1.875f, -0.771f)
                horizontalLineTo(33.75f)
                quadToRelative(1.083f, 0f, 1.854f, 0.771f)
                quadToRelative(0.771f, 0.771f, 0.771f, 1.854f)
                verticalLineToRelative(22.208f)
                quadToRelative(0f, 1.084f, -0.771f, 1.875f)
                quadToRelative(-0.771f, 0.792f, -1.854f, 0.792f)
                close()
                moveToRelative(0f, -2.667f)
                horizontalLineTo(33.75f)
                verticalLineTo(6.25f)
                horizontalLineTo(11.542f)
                verticalLineToRelative(22.208f)
                close()
                moveTo(6.25f, 36.375f)
                quadToRelative(-1.083f, 0f, -1.854f, -0.771f)
                quadToRelative(-0.771f, -0.771f, -0.771f, -1.854f)
                verticalLineTo(10.208f)
                quadToRelative(0f, -0.541f, 0.375f, -0.937f)
                reflectiveQuadToRelative(0.917f, -0.396f)
                quadToRelative(0.583f, 0f, 0.958f, 0.396f)
                reflectiveQuadToRelative(0.375f, 0.937f)
                verticalLineTo(33.75f)
                horizontalLineToRelative(23.542f)
                quadToRelative(0.541f, 0f, 0.937f, 0.396f)
                reflectiveQuadToRelative(0.396f, 0.937f)
                quadToRelative(0f, 0.542f, -0.396f, 0.917f)
                reflectiveQuadToRelative(-0.937f, 0.375f)
                close()
                moveTo(11.542f, 6.25f)
                verticalLineToRelative(22.208f)
                verticalLineTo(6.25f)
                close()
                moveToRelative(5.583f, 18.542f)
                quadToRelative(0.292f, 0f, 0.542f, -0.167f)
                quadToRelative(0.25f, -0.167f, 0.333f, -0.417f)
                lineToRelative(1.333f, -3.458f)
                horizontalLineTo(26f)
                lineToRelative(1.333f, 3.417f)
                quadToRelative(0.084f, 0.291f, 0.334f, 0.458f)
                quadToRelative(0.25f, 0.167f, 0.541f, 0.167f)
                quadToRelative(0.459f, 0f, 0.73f, -0.396f)
                quadToRelative(0.27f, -0.396f, 0.104f, -0.854f)
                lineTo(24f, 10.833f)
                quadToRelative(-0.167f, -0.416f, -0.521f, -0.666f)
                quadToRelative(-0.354f, -0.25f, -0.812f, -0.25f)
                quadToRelative(-0.459f, 0f, -0.813f, 0.25f)
                quadToRelative(-0.354f, 0.25f, -0.521f, 0.666f)
                lineTo(16.25f, 23.5f)
                quadToRelative(-0.167f, 0.458f, 0.104f, 0.875f)
                reflectiveQuadToRelative(0.771f, 0.417f)
                close()
            }
        }.build()
    }
}