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
fun rememberAddIcon(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "add",
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
                moveTo(20f, 31.458f)
                quadToRelative(-0.542f, 0f, -0.917f, -0.396f)
                quadToRelative(-0.375f, -0.395f, -0.375f, -0.937f)
                verticalLineToRelative(-8.833f)
                horizontalLineTo(9.875f)
                quadToRelative(-0.583f, 0f, -0.958f, -0.375f)
                reflectiveQuadTo(8.542f, 20f)
                quadToRelative(0f, -0.583f, 0.375f, -0.958f)
                reflectiveQuadToRelative(0.958f, -0.375f)
                horizontalLineToRelative(8.833f)
                verticalLineTo(9.833f)
                quadToRelative(0f, -0.541f, 0.375f, -0.916f)
                reflectiveQuadTo(20f, 8.542f)
                quadToRelative(0.542f, 0f, 0.938f, 0.375f)
                quadToRelative(0.395f, 0.375f, 0.395f, 0.916f)
                verticalLineToRelative(8.834f)
                horizontalLineToRelative(8.792f)
                quadToRelative(0.583f, 0f, 0.958f, 0.395f)
                quadToRelative(0.375f, 0.396f, 0.375f, 0.938f)
                quadToRelative(0f, 0.542f, -0.375f, 0.917f)
                reflectiveQuadToRelative(-0.958f, 0.375f)
                horizontalLineToRelative(-8.792f)
                verticalLineToRelative(8.833f)
                quadToRelative(0f, 0.542f, -0.395f, 0.937f)
                quadToRelative(-0.396f, 0.396f, -0.938f, 0.396f)
                close()
            }
        }.build()
    }
}