package io.github.jan.kex.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val HomeIcon: ImageVector
    get() {
        if(_homeIcon != null) {
            return _homeIcon!!
        }
        _homeIcon = ImageVector.Builder(
            name = "home",
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
                moveTo(6.917f, 34.75f)
                verticalLineTo(15.125f)
                lineToRelative(13.125f, -9.833f)
                lineToRelative(13.041f, 9.833f)
                verticalLineTo(34.75f)
                horizontalLineToRelative(-9.666f)
                verticalLineTo(23.167f)
                horizontalLineToRelative(-6.834f)
                verticalLineTo(34.75f)
                close()
                moveToRelative(2.625f, -2.625f)
                horizontalLineToRelative(4.416f)
                verticalLineTo(20.542f)
                horizontalLineToRelative(12.084f)
                verticalLineToRelative(11.583f)
                horizontalLineToRelative(4.416f)
                verticalLineTo(16.417f)
                lineTo(20.042f, 8.583f)
                lineToRelative(-10.5f, 7.834f)
                close()
                moveTo(20f, 20.333f)
                close()
            }
        }.build()
        return _homeIcon!!
    }
private var _homeIcon: ImageVector? = null