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
fun rememberSelectAll(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "select_all",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
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
                moveTo(5.25f, 7.875f)
                quadToRelative(0f, -1.042f, 0.792f, -1.833f)
                quadToRelative(0.791f, -0.792f, 1.833f, -0.792f)
                verticalLineToRelative(2.625f)
                close()
                moveToRelative(0f, 13.833f)
                verticalLineToRelative(-3.416f)
                horizontalLineToRelative(2.625f)
                verticalLineToRelative(3.416f)
                close()
                moveToRelative(6.167f, 13.084f)
                verticalLineToRelative(-2.667f)
                horizontalLineToRelative(3.375f)
                verticalLineToRelative(2.667f)
                close()
                moveToRelative(-6.167f, -20f)
                verticalLineToRelative(-3.375f)
                horizontalLineToRelative(2.625f)
                verticalLineToRelative(3.375f)
                close()
                moveToRelative(13.083f, -6.917f)
                verticalLineTo(5.25f)
                horizontalLineToRelative(3.375f)
                verticalLineToRelative(2.625f)
                close()
                moveToRelative(13.792f, 0f)
                verticalLineTo(5.25f)
                quadToRelative(1.083f, 0f, 1.875f, 0.792f)
                quadToRelative(0.792f, 0.791f, 0.792f, 1.833f)
                close()
                moveTo(7.875f, 34.792f)
                quadToRelative(-1.042f, 0f, -1.833f, -0.792f)
                quadToRelative(-0.792f, -0.792f, -0.792f, -1.875f)
                horizontalLineToRelative(2.625f)
                close()
                moveTo(5.25f, 28.625f)
                verticalLineToRelative(-3.417f)
                horizontalLineToRelative(2.625f)
                verticalLineToRelative(3.417f)
                close()
                moveToRelative(6.167f, -20.75f)
                verticalLineTo(5.25f)
                horizontalLineToRelative(3.375f)
                verticalLineToRelative(2.625f)
                close()
                moveToRelative(6.916f, 26.917f)
                verticalLineToRelative(-2.667f)
                horizontalLineToRelative(3.375f)
                verticalLineToRelative(2.667f)
                close()
                moveToRelative(13.792f, -13.084f)
                verticalLineToRelative(-3.416f)
                horizontalLineToRelative(2.667f)
                verticalLineToRelative(3.416f)
                close()
                moveToRelative(0f, 13.084f)
                verticalLineToRelative(-2.667f)
                horizontalLineToRelative(2.667f)
                quadToRelative(0f, 1.083f, -0.792f, 1.875f)
                reflectiveQuadToRelative(-1.875f, 0.792f)
                close()
                moveToRelative(0f, -20f)
                verticalLineToRelative(-3.375f)
                horizontalLineToRelative(2.667f)
                verticalLineToRelative(3.375f)
                close()
                moveToRelative(0f, 13.833f)
                verticalLineToRelative(-3.417f)
                horizontalLineToRelative(2.667f)
                verticalLineToRelative(3.417f)
                close()
                moveToRelative(-6.917f, 6.167f)
                verticalLineToRelative(-2.667f)
                horizontalLineToRelative(3.417f)
                verticalLineToRelative(2.667f)
                close()
                moveToRelative(0f, -26.917f)
                verticalLineTo(5.25f)
                horizontalLineToRelative(3.417f)
                verticalLineToRelative(2.625f)
                close()
                moveToRelative(-12.5f, 20.75f)
                quadToRelative(-0.541f, 0f, -0.916f, -0.375f)
                reflectiveQuadToRelative(-0.375f, -0.958f)
                verticalLineTo(12.708f)
                quadToRelative(0f, -0.541f, 0.375f, -0.916f)
                reflectiveQuadToRelative(0.916f, -0.375f)
                horizontalLineToRelative(14.584f)
                quadToRelative(0.583f, 0f, 0.958f, 0.375f)
                reflectiveQuadToRelative(0.375f, 0.916f)
                verticalLineToRelative(14.584f)
                quadToRelative(0f, 0.583f, -0.375f, 0.958f)
                reflectiveQuadToRelative(-0.958f, 0.375f)
                close()
                moveToRelative(1.334f, -2.667f)
                horizontalLineToRelative(11.916f)
                verticalLineTo(14.042f)
                horizontalLineTo(14.042f)
                close()
            }
        }.build()
    }
}