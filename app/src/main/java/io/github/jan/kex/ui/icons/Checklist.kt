package io.github.jan.kex.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val ChecklistIcon: ImageVector
    get() {
        if(_checklistIcon != null) {
            return _checklistIcon!!
        }
        _checklistIcon = ImageVector.Builder(
            name = "checklist",
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
                moveTo(9.25f, 31.167f)
                lineToRelative(-5.833f, -5.792f)
                lineTo(5.25f, 23.5f)
                lineToRelative(4f, 3.958f)
                lineToRelative(7.333f, -7.333f)
                lineTo(18.417f, 22f)
                close()
                moveToRelative(0f, -13.334f)
                lineToRelative(-5.833f, -5.791f)
                lineToRelative(1.833f, -1.875f)
                lineToRelative(4f, 3.958f)
                lineToRelative(7.333f, -7.333f)
                lineToRelative(1.834f, 1.875f)
                close()
                moveToRelative(12.458f, 10.125f)
                verticalLineToRelative(-2.625f)
                horizontalLineToRelative(14.875f)
                verticalLineToRelative(2.625f)
                close()
                moveToRelative(0f, -13.333f)
                verticalLineTo(12f)
                horizontalLineToRelative(14.875f)
                verticalLineToRelative(2.625f)
                close()
            }
        }.build()
        return _checklistIcon!!
    }
private var _checklistIcon: ImageVector? = null