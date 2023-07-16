package io.github.jan.kex.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val ExamIcon: ImageVector
    get() {
        if(_examIcon != null) {
            return _examIcon!!
        }
        _examIcon = ImageVector.Builder(
            name = "pending_actions",
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
                moveTo(28.875f, 36.375f)
                quadToRelative(-3.25f, 0f, -5.542f, -2.292f)
                quadToRelative(-2.291f, -2.291f, -2.291f, -5.541f)
                quadToRelative(0f, -3.25f, 2.291f, -5.542f)
                quadToRelative(2.292f, -2.292f, 5.542f, -2.292f)
                reflectiveQuadTo(34.417f, 23f)
                quadToRelative(2.291f, 2.292f, 2.291f, 5.542f)
                reflectiveQuadToRelative(-2.291f, 5.541f)
                quadToRelative(-2.292f, 2.292f, -5.542f, 2.292f)
                close()
                moveToRelative(1.917f, -4.333f)
                quadToRelative(0.25f, 0.25f, 0.562f, 0.25f)
                quadToRelative(0.313f, 0f, 0.563f, -0.25f)
                quadToRelative(0.25f, -0.25f, 0.25f, -0.584f)
                quadToRelative(0f, -0.333f, -0.25f, -0.541f)
                lineToRelative(-2.5f, -2.5f)
                verticalLineToRelative(-3.834f)
                quadToRelative(0f, -0.291f, -0.229f, -0.541f)
                quadToRelative(-0.23f, -0.25f, -0.563f, -0.25f)
                quadToRelative(-0.333f, 0f, -0.583f, 0.25f)
                quadToRelative(-0.25f, 0.25f, -0.25f, 0.583f)
                verticalLineToRelative(3.833f)
                quadToRelative(0f, 0.25f, 0.104f, 0.5f)
                reflectiveQuadToRelative(0.271f, 0.417f)
                close()
                moveTo(7.917f, 34.333f)
                quadToRelative(-1.084f, 0f, -1.855f, -0.771f)
                quadToRelative(-0.77f, -0.77f, -0.77f, -1.854f)
                verticalLineTo(8f)
                quadToRelative(0f, -1.083f, 0.687f, -1.854f)
                quadToRelative(0.688f, -0.771f, 1.396f, -0.771f)
                horizontalLineToRelative(8f)
                quadToRelative(0.333f, -1.458f, 1.479f, -2.396f)
                quadTo(18f, 2.042f, 19.5f, 2.042f)
                reflectiveQuadToRelative(2.646f, 0.937f)
                quadToRelative(1.146f, 0.938f, 1.479f, 2.396f)
                horizontalLineToRelative(8f)
                quadToRelative(1.083f, 0f, 1.854f, 0.771f)
                quadToRelative(0.771f, 0.771f, 0.771f, 1.854f)
                verticalLineToRelative(11.583f)
                quadToRelative(-0.667f, -0.416f, -1.292f, -0.666f)
                quadToRelative(-0.625f, -0.25f, -1.333f, -0.459f)
                verticalLineTo(8f)
                horizontalLineTo(27.5f)
                verticalLineToRelative(2.5f)
                quadToRelative(0f, 1.083f, -0.771f, 1.854f)
                quadToRelative(-0.771f, 0.771f, -1.854f, 0.771f)
                horizontalLineTo(14.667f)
                quadToRelative(-1.084f, 0f, -1.855f, -0.771f)
                quadToRelative(-0.77f, -0.771f, -0.77f, -1.854f)
                verticalLineTo(8f)
                horizontalLineTo(7.917f)
                verticalLineToRelative(23.708f)
                horizontalLineToRelative(10.958f)
                quadToRelative(0.208f, 0.709f, 0.5f, 1.313f)
                quadToRelative(0.292f, 0.604f, 0.75f, 1.312f)
                close()
                moveTo(20f, 7.792f)
                quadToRelative(0.667f, 0f, 1.146f, -0.479f)
                quadToRelative(0.479f, -0.48f, 0.479f, -1.146f)
                quadToRelative(0f, -0.667f, -0.479f, -1.125f)
                quadToRelative(-0.479f, -0.459f, -1.104f, -0.459f)
                quadToRelative(-0.709f, 0f, -1.167f, 0.459f)
                quadToRelative(-0.458f, 0.458f, -0.458f, 1.125f)
                quadToRelative(0f, 0.666f, 0.458f, 1.146f)
                quadToRelative(0.458f, 0.479f, 1.125f, 0.479f)
                close()
            }
        }.build()
        return _examIcon!!
    }
private var _examIcon: ImageVector? = null