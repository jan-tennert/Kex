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
fun rememberDateRange(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "date_range",
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
                moveTo(13f, 23.458f)
                quadToRelative(-0.708f, 0f, -1.167f, -0.479f)
                quadToRelative(-0.458f, -0.479f, -0.458f, -1.187f)
                quadToRelative(0f, -0.667f, 0.458f, -1.146f)
                quadToRelative(0.459f, -0.479f, 1.167f, -0.479f)
                reflectiveQuadToRelative(1.167f, 0.479f)
                quadToRelative(0.458f, 0.479f, 0.458f, 1.146f)
                quadToRelative(0f, 0.708f, -0.458f, 1.187f)
                quadToRelative(-0.459f, 0.479f, -1.167f, 0.479f)
                close()
                moveToRelative(7.083f, 0f)
                quadToRelative(-0.666f, 0f, -1.145f, -0.479f)
                quadToRelative(-0.48f, -0.479f, -0.48f, -1.187f)
                quadToRelative(0f, -0.667f, 0.48f, -1.146f)
                quadToRelative(0.479f, -0.479f, 1.145f, -0.479f)
                quadToRelative(0.709f, 0f, 1.188f, 0.479f)
                reflectiveQuadToRelative(0.479f, 1.146f)
                quadToRelative(0f, 0.708f, -0.479f, 1.187f)
                quadToRelative(-0.479f, 0.479f, -1.188f, 0.479f)
                close()
                moveToRelative(6.875f, 0f)
                quadToRelative(-0.666f, 0f, -1.146f, -0.479f)
                quadToRelative(-0.479f, -0.479f, -0.479f, -1.187f)
                quadToRelative(0f, -0.667f, 0.479f, -1.146f)
                quadToRelative(0.48f, -0.479f, 1.146f, -0.479f)
                quadToRelative(0.709f, 0f, 1.188f, 0.479f)
                reflectiveQuadToRelative(0.479f, 1.146f)
                quadToRelative(0f, 0.708f, -0.479f, 1.187f)
                quadToRelative(-0.479f, 0.479f, -1.188f, 0.479f)
                close()
                moveTo(7.875f, 36.375f)
                quadToRelative(-1.042f, 0f, -1.833f, -0.771f)
                quadToRelative(-0.792f, -0.771f, -0.792f, -1.854f)
                verticalLineTo(8.958f)
                quadToRelative(0f, -1.041f, 0.792f, -1.833f)
                quadToRelative(0.791f, -0.792f, 1.833f, -0.792f)
                horizontalLineToRelative(2.5f)
                verticalLineTo(4.875f)
                quadToRelative(0f, -0.542f, 0.396f, -0.937f)
                quadToRelative(0.396f, -0.396f, 0.979f, -0.396f)
                reflectiveQuadToRelative(1f, 0.416f)
                quadToRelative(0.417f, 0.417f, 0.417f, 0.959f)
                verticalLineToRelative(1.416f)
                horizontalLineToRelative(13.666f)
                verticalLineTo(4.875f)
                quadToRelative(0f, -0.542f, 0.396f, -0.937f)
                quadToRelative(0.396f, -0.396f, 0.979f, -0.396f)
                quadToRelative(0.584f, 0f, 1f, 0.416f)
                quadToRelative(0.417f, 0.417f, 0.417f, 0.959f)
                verticalLineToRelative(1.416f)
                horizontalLineToRelative(2.5f)
                quadToRelative(1.042f, 0f, 1.833f, 0.792f)
                quadToRelative(0.792f, 0.792f, 0.792f, 1.833f)
                verticalLineTo(33.75f)
                quadToRelative(0f, 1.083f, -0.792f, 1.854f)
                quadToRelative(-0.791f, 0.771f, -1.833f, 0.771f)
                close()
                moveToRelative(0f, -2.625f)
                horizontalLineToRelative(24.25f)
                verticalLineTo(16.458f)
                horizontalLineTo(7.875f)
                verticalLineTo(33.75f)
                close()
                moveToRelative(0f, -19.958f)
                horizontalLineToRelative(24.25f)
                verticalLineTo(8.958f)
                horizontalLineTo(7.875f)
                close()
                moveToRelative(0f, 0f)
                verticalLineTo(8.958f)
                verticalLineToRelative(4.834f)
                close()
            }
        }.build()
    }
}