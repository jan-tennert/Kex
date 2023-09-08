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
fun rememberRobotIcon(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "robot",
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
                moveTo(7.708f, 34.917f)
                quadToRelative(-1.083f, 0f, -1.854f, -0.771f)
                quadToRelative(-0.771f, -0.771f, -0.771f, -1.854f)
                verticalLineTo(15f)
                quadToRelative(0f, -4.125f, 2.896f, -7.042f)
                quadTo(10.875f, 5.042f, 15f, 5.042f)
                horizontalLineToRelative(10f)
                quadToRelative(4.125f, 0f, 7.042f, 2.916f)
                quadToRelative(2.916f, 2.917f, 2.916f, 7.042f)
                verticalLineToRelative(17.292f)
                quadToRelative(0f, 1.083f, -0.791f, 1.854f)
                quadToRelative(-0.792f, 0.771f, -1.875f, 0.771f)
                close()
                moveToRelative(0f, -2.625f)
                horizontalLineTo(32.292f)
                verticalLineTo(15f)
                quadToRelative(0f, -3.042f, -2.125f, -5.167f)
                reflectiveQuadTo(25f, 7.708f)
                horizontalLineTo(15f)
                quadToRelative(-3.042f, 0f, -5.167f, 2.125f)
                reflectiveQuadTo(7.708f, 15f)
                verticalLineToRelative(17.292f)
                close()
                moveTo(15f, 19.625f)
                quadToRelative(-1.25f, 0f, -2.104f, -0.875f)
                quadToRelative(-0.854f, -0.875f, -0.854f, -2.083f)
                quadToRelative(0f, -1.25f, 0.875f, -2.125f)
                reflectiveQuadTo(15f, 13.667f)
                quadToRelative(1.25f, 0f, 2.125f, 0.875f)
                reflectiveQuadTo(18f, 16.667f)
                quadToRelative(0f, 1.25f, -0.875f, 2.104f)
                quadToRelative(-0.875f, 0.854f, -2.125f, 0.854f)
                close()
                moveToRelative(10f, 0f)
                quadToRelative(-1.25f, 0f, -2.104f, -0.875f)
                quadToRelative(-0.854f, -0.875f, -0.854f, -2.083f)
                quadToRelative(0f, -1.25f, 0.875f, -2.125f)
                reflectiveQuadTo(25f, 13.667f)
                quadToRelative(1.25f, 0f, 2.125f, 0.875f)
                reflectiveQuadTo(28f, 16.667f)
                quadToRelative(0f, 1.25f, -0.875f, 2.104f)
                quadToRelative(-0.875f, 0.854f, -2.125f, 0.854f)
                close()
                moveTo(12.042f, 32.292f)
                verticalLineToRelative(-4.334f)
                quadToRelative(0f, -1.083f, 0.75f, -1.854f)
                quadToRelative(0.75f, -0.771f, 1.875f, -0.771f)
                horizontalLineToRelative(10.708f)
                quadToRelative(1.083f, 0f, 1.854f, 0.771f)
                quadToRelative(0.771f, 0.771f, 0.771f, 1.854f)
                verticalLineToRelative(4.334f)
                horizontalLineToRelative(-2.625f)
                verticalLineToRelative(-4.334f)
                horizontalLineToRelative(-4.042f)
                verticalLineToRelative(4.334f)
                horizontalLineToRelative(-2.625f)
                verticalLineToRelative(-4.334f)
                horizontalLineToRelative(-4.041f)
                verticalLineToRelative(4.334f)
                close()
                moveToRelative(-4.334f, 0f)
                horizontalLineToRelative(24.584f)
                horizontalLineTo(7.708f)
                close()
            }
        }.build()
    }
}