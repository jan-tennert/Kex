package io.github.jan.kex.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val EditIcon: ImageVector
    get() {
        if(_editIcon != null) {
            return _editIcon!!
        }
        _editIcon = ImageVector.Builder(
            name = "edit",
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
                moveTo(7.958f, 32.042f)
                horizontalLineToRelative(1.875f)
                lineTo(27f, 14.875f)
                lineTo(25.083f, 13f)
                lineTo(7.958f, 30.167f)
                close()
                moveTo(32.583f, 13f)
                lineToRelative(-5.625f, -5.583f)
                lineToRelative(1.875f, -1.875f)
                quadToRelative(0.75f, -0.75f, 1.875f, -0.75f)
                reflectiveQuadToRelative(1.875f, 0.791f)
                lineToRelative(1.875f, 1.875f)
                quadToRelative(0.75f, 0.75f, 0.75f, 1.855f)
                quadToRelative(0f, 1.104f, -0.75f, 1.854f)
                close()
                moveTo(6.625f, 34.667f)
                quadToRelative(-0.583f, 0f, -0.958f, -0.375f)
                reflectiveQuadToRelative(-0.375f, -0.917f)
                verticalLineToRelative(-3.792f)
                quadToRelative(0f, -0.25f, 0.104f, -0.479f)
                quadToRelative(0.104f, -0.229f, 0.312f, -0.437f)
                lineTo(25.125f, 9.25f)
                lineToRelative(5.625f, 5.625f)
                lineToRelative(-19.458f, 19.417f)
                quadToRelative(-0.167f, 0.208f, -0.417f, 0.291f)
                quadToRelative(-0.25f, 0.084f, -0.5f, 0.084f)
                close()
                moveToRelative(19.417f, -20.75f)
                lineTo(25.083f, 13f)
                lineTo(27f, 14.875f)
                close()
            }
        }.build()
        return _editIcon!!
    }
private var _editIcon: ImageVector? = null