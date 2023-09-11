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
fun rememberAddLinkIcon(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "add_link",
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
                moveTo(11.708f, 28.208f)
                quadToRelative(-3.458f, 0f, -5.791f, -2.354f)
                quadToRelative(-2.334f, -2.354f, -2.334f, -5.812f)
                quadToRelative(0f, -3.417f, 2.334f, -5.771f)
                quadToRelative(2.333f, -2.354f, 5.791f, -2.354f)
                horizontalLineToRelative(5.5f)
                quadToRelative(0.584f, 0f, 0.959f, 0.396f)
                quadToRelative(0.375f, 0.395f, 0.375f, 0.937f)
                reflectiveQuadToRelative(-0.375f, 0.917f)
                quadToRelative(-0.375f, 0.375f, -0.959f, 0.375f)
                horizontalLineToRelative(-5.5f)
                quadToRelative(-2.333f, 0f, -3.916f, 1.583f)
                quadToRelative(-1.584f, 1.583f, -1.584f, 3.917f)
                quadToRelative(0f, 2.375f, 1.584f, 3.937f)
                quadToRelative(1.583f, 1.563f, 3.916f, 1.563f)
                horizontalLineToRelative(5.5f)
                quadToRelative(0.584f, 0f, 0.959f, 0.396f)
                quadToRelative(0.375f, 0.395f, 0.375f, 0.937f)
                reflectiveQuadToRelative(-0.375f, 0.937f)
                quadToRelative(-0.375f, 0.396f, -0.959f, 0.396f)
                close()
                moveToRelative(3.167f, -6.833f)
                quadToRelative(-0.583f, 0f, -0.958f, -0.396f)
                reflectiveQuadToRelative(-0.375f, -0.937f)
                quadToRelative(0f, -0.542f, 0.375f, -0.938f)
                quadToRelative(0.375f, -0.396f, 0.958f, -0.396f)
                horizontalLineToRelative(10.167f)
                quadToRelative(0.541f, 0f, 0.916f, 0.396f)
                reflectiveQuadToRelative(0.375f, 0.938f)
                quadToRelative(0f, 0.541f, -0.375f, 0.937f)
                reflectiveQuadToRelative(-0.916f, 0.396f)
                close()
                moveToRelative(21.5f, -1.333f)
                horizontalLineTo(33.75f)
                quadToRelative(0f, -2.375f, -1.583f, -3.938f)
                quadToRelative(-1.584f, -1.562f, -3.917f, -1.562f)
                horizontalLineToRelative(-5.542f)
                quadToRelative(-0.541f, 0f, -0.916f, -0.396f)
                reflectiveQuadToRelative(-0.375f, -0.938f)
                quadToRelative(0f, -0.541f, 0.375f, -0.937f)
                reflectiveQuadToRelative(0.916f, -0.396f)
                horizontalLineToRelative(5.542f)
                quadToRelative(3.417f, 0f, 5.771f, 2.354f)
                reflectiveQuadToRelative(2.354f, 5.813f)
                close()
                moveToRelative(-6.25f, 13.125f)
                quadToRelative(-0.542f, 0f, -0.917f, -0.375f)
                reflectiveQuadToRelative(-0.375f, -0.959f)
                verticalLineToRelative(-3.625f)
                horizontalLineToRelative(-3.625f)
                quadToRelative(-0.583f, 0f, -0.958f, -0.375f)
                reflectiveQuadToRelative(-0.375f, -0.916f)
                quadToRelative(0f, -0.584f, 0.375f, -0.959f)
                reflectiveQuadToRelative(0.958f, -0.375f)
                horizontalLineToRelative(3.625f)
                verticalLineToRelative(-3.625f)
                quadToRelative(0f, -0.541f, 0.375f, -0.937f)
                reflectiveQuadToRelative(0.917f, -0.396f)
                quadToRelative(0.583f, 0f, 0.958f, 0.396f)
                reflectiveQuadToRelative(0.375f, 0.937f)
                verticalLineToRelative(3.625f)
                horizontalLineToRelative(3.625f)
                quadToRelative(0.542f, 0f, 0.938f, 0.396f)
                quadToRelative(0.396f, 0.396f, 0.396f, 0.938f)
                quadToRelative(0f, 0.541f, -0.396f, 0.916f)
                reflectiveQuadToRelative(-0.938f, 0.375f)
                horizontalLineToRelative(-3.625f)
                verticalLineToRelative(3.625f)
                quadToRelative(0f, 0.584f, -0.375f, 0.959f)
                reflectiveQuadToRelative(-0.958f, 0.375f)
                close()
            }
        }.build()
    }
}