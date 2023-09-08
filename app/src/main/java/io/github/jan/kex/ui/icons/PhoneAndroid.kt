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
fun rememberPhoneAndroidIcon(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "phone_android",
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
                moveTo(17.5f, 34.167f)
                horizontalLineToRelative(5.042f)
                quadToRelative(0.291f, 0f, 0.541f, -0.25f)
                quadToRelative(0.25f, -0.25f, 0.25f, -0.584f)
                quadToRelative(0f, -0.333f, -0.25f, -0.583f)
                quadToRelative(-0.25f, -0.25f, -0.583f, -0.25f)
                horizontalLineToRelative(-5f)
                quadToRelative(-0.333f, 0f, -0.583f, 0.25f)
                quadToRelative(-0.25f, 0.25f, -0.25f, 0.583f)
                quadToRelative(0f, 0.334f, 0.25f, 0.584f)
                quadToRelative(0.25f, 0.25f, 0.583f, 0.25f)
                close()
                moveToRelative(-6.292f, 3.875f)
                quadToRelative(-1.083f, 0f, -1.875f, -0.771f)
                quadToRelative(-0.791f, -0.771f, -0.791f, -1.854f)
                verticalLineTo(4.583f)
                quadToRelative(0f, -1.083f, 0.791f, -1.854f)
                quadToRelative(0.792f, -0.771f, 1.875f, -0.771f)
                horizontalLineToRelative(17.584f)
                quadToRelative(1.083f, 0f, 1.875f, 0.771f)
                quadToRelative(0.791f, 0.771f, 0.791f, 1.854f)
                verticalLineToRelative(30.834f)
                quadToRelative(0f, 1.083f, -0.791f, 1.854f)
                quadToRelative(-0.792f, 0.771f, -1.875f, 0.771f)
                close()
                moveToRelative(0f, -9.417f)
                horizontalLineToRelative(17.584f)
                verticalLineTo(8.667f)
                horizontalLineTo(11.208f)
                close()
                moveToRelative(0f, 2.625f)
                verticalLineToRelative(4.167f)
                horizontalLineToRelative(17.584f)
                verticalLineTo(31.25f)
                close()
                moveToRelative(0f, -25.208f)
                horizontalLineToRelative(17.584f)
                verticalLineTo(4.583f)
                horizontalLineTo(11.208f)
                close()
                moveToRelative(0f, -1.459f)
                verticalLineToRelative(1.459f)
                verticalLineToRelative(-1.459f)
                close()
                moveToRelative(0f, 30.834f)
                verticalLineTo(31.25f)
                verticalLineToRelative(4.167f)
                close()
            }
        }.build()
    }
}