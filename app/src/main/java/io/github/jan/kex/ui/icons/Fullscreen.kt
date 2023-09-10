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
fun rememberFullscreen(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "fullscreen",
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
                moveTo(9.75f, 31.542f)
                quadToRelative(-0.542f, 0f, -0.917f, -0.375f)
                reflectiveQuadToRelative(-0.375f, -0.917f)
                verticalLineToRelative(-5.458f)
                quadToRelative(0f, -0.5f, 0.375f, -0.896f)
                reflectiveQuadToRelative(0.959f, -0.396f)
                quadToRelative(0.541f, 0f, 0.916f, 0.396f)
                reflectiveQuadToRelative(0.375f, 0.896f)
                verticalLineToRelative(4.125f)
                horizontalLineToRelative(4.125f)
                quadToRelative(0.5f, 0f, 0.896f, 0.395f)
                quadToRelative(0.396f, 0.396f, 0.396f, 0.938f)
                quadToRelative(0f, 0.542f, -0.396f, 0.917f)
                reflectiveQuadToRelative(-0.896f, 0.375f)
                close()
                moveToRelative(0f, -15.042f)
                quadToRelative(-0.542f, 0f, -0.917f, -0.396f)
                reflectiveQuadToRelative(-0.375f, -0.937f)
                verticalLineTo(9.75f)
                quadToRelative(0f, -0.542f, 0.375f, -0.917f)
                reflectiveQuadToRelative(0.917f, -0.375f)
                horizontalLineToRelative(5.458f)
                quadToRelative(0.5f, 0f, 0.896f, 0.375f)
                reflectiveQuadToRelative(0.396f, 0.917f)
                quadToRelative(0f, 0.583f, -0.396f, 0.958f)
                reflectiveQuadToRelative(-0.896f, 0.375f)
                horizontalLineToRelative(-4.125f)
                verticalLineToRelative(4.084f)
                quadToRelative(0f, 0.541f, -0.395f, 0.937f)
                quadToRelative(-0.396f, 0.396f, -0.938f, 0.396f)
                close()
                moveToRelative(15.083f, 15.042f)
                quadToRelative(-0.541f, 0f, -0.937f, -0.375f)
                reflectiveQuadToRelative(-0.396f, -0.959f)
                quadToRelative(0f, -0.541f, 0.396f, -0.916f)
                reflectiveQuadToRelative(0.937f, -0.375f)
                horizontalLineToRelative(4.084f)
                verticalLineToRelative(-4.125f)
                quadToRelative(0f, -0.5f, 0.395f, -0.896f)
                quadToRelative(0.396f, -0.396f, 0.938f, -0.396f)
                quadToRelative(0.542f, 0f, 0.917f, 0.396f)
                reflectiveQuadToRelative(0.375f, 0.896f)
                verticalLineToRelative(5.458f)
                quadToRelative(0f, 0.542f, -0.375f, 0.917f)
                reflectiveQuadToRelative(-0.917f, 0.375f)
                close()
                moveTo(30.25f, 16.5f)
                quadToRelative(-0.583f, 0f, -0.958f, -0.396f)
                reflectiveQuadToRelative(-0.375f, -0.937f)
                verticalLineToRelative(-4.084f)
                horizontalLineToRelative(-4.084f)
                quadToRelative(-0.541f, 0f, -0.937f, -0.395f)
                quadToRelative(-0.396f, -0.396f, -0.396f, -0.938f)
                quadToRelative(0f, -0.542f, 0.396f, -0.917f)
                reflectiveQuadToRelative(0.937f, -0.375f)
                horizontalLineToRelative(5.417f)
                quadToRelative(0.542f, 0f, 0.917f, 0.375f)
                reflectiveQuadToRelative(0.375f, 0.917f)
                verticalLineToRelative(5.417f)
                quadToRelative(0f, 0.541f, -0.375f, 0.937f)
                reflectiveQuadToRelative(-0.917f, 0.396f)
                close()
            }
        }.build()
    }
}