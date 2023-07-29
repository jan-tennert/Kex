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
fun rememberFormatStrikethrough(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "format_strikethrough",
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
                moveTo(4.75f, 22.958f)
                quadToRelative(-0.542f, 0f, -0.917f, -0.375f)
                reflectiveQuadToRelative(-0.375f, -0.916f)
                quadToRelative(0f, -0.584f, 0.375f, -0.959f)
                reflectiveQuadToRelative(0.917f, -0.375f)
                horizontalLineToRelative(30.5f)
                quadToRelative(0.542f, 0f, 0.917f, 0.396f)
                reflectiveQuadToRelative(0.375f, 0.938f)
                quadToRelative(0f, 0.541f, -0.375f, 0.916f)
                reflectiveQuadToRelative(-0.917f, 0.375f)
                close()
                moveToRelative(13.125f, -5.25f)
                verticalLineTo(11f)
                horizontalLineToRelative(-7.333f)
                quadToRelative(-0.875f, 0f, -1.48f, -0.625f)
                quadToRelative(-0.604f, -0.625f, -0.604f, -1.5f)
                reflectiveQuadToRelative(0.604f, -1.479f)
                quadToRelative(0.605f, -0.604f, 1.48f, -0.604f)
                horizontalLineToRelative(18.916f)
                quadToRelative(0.875f, 0f, 1.48f, 0.604f)
                quadToRelative(0.604f, 0.604f, 0.604f, 1.521f)
                quadToRelative(0f, 0.875f, -0.604f, 1.479f)
                quadToRelative(-0.605f, 0.604f, -1.48f, 0.604f)
                horizontalLineToRelative(-7.333f)
                verticalLineToRelative(6.708f)
                close()
                moveTo(20f, 33.208f)
                quadToRelative(-0.875f, 0f, -1.5f, -0.604f)
                reflectiveQuadToRelative(-0.625f, -1.479f)
                verticalLineToRelative(-5.5f)
                horizontalLineToRelative(4.25f)
                verticalLineToRelative(5.5f)
                quadToRelative(0f, 0.875f, -0.625f, 1.479f)
                quadToRelative(-0.625f, 0.604f, -1.5f, 0.604f)
                close()
            }
        }.build()
    }
}