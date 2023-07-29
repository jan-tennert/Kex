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
fun rememberFormatListNumbered(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "format_list_numbered",
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
                moveTo(5.917f, 33.208f)
                quadToRelative(-0.292f, 0f, -0.521f, -0.229f)
                quadToRelative(-0.229f, -0.229f, -0.229f, -0.521f)
                quadToRelative(0f, -0.291f, 0.229f, -0.52f)
                quadToRelative(0.229f, -0.23f, 0.521f, -0.23f)
                horizontalLineToRelative(2.708f)
                verticalLineToRelative(-1f)
                horizontalLineToRelative(-1f)
                quadToRelative(-0.292f, 0f, -0.521f, -0.229f)
                quadToRelative(-0.229f, -0.229f, -0.229f, -0.521f)
                quadToRelative(0f, -0.25f, 0.229f, -0.479f)
                quadToRelative(0.229f, -0.229f, 0.521f, -0.229f)
                horizontalLineToRelative(1f)
                verticalLineToRelative(-1f)
                horizontalLineTo(5.917f)
                quadToRelative(-0.292f, 0f, -0.521f, -0.229f)
                quadToRelative(-0.229f, -0.229f, -0.229f, -0.521f)
                quadToRelative(0f, -0.292f, 0.229f, -0.5f)
                quadToRelative(0.229f, -0.208f, 0.521f, -0.208f)
                horizontalLineToRelative(3.416f)
                quadToRelative(0.25f, 0f, 0.479f, 0.229f)
                quadToRelative(0.23f, 0.229f, 0.23f, 0.479f)
                verticalLineToRelative(4.958f)
                quadToRelative(0f, 0.292f, -0.23f, 0.521f)
                quadToRelative(-0.229f, 0.229f, -0.52f, 0.229f)
                close()
                moveToRelative(1.708f, -19.916f)
                quadToRelative(-0.292f, 0f, -0.521f, -0.229f)
                quadToRelative(-0.229f, -0.23f, -0.229f, -0.521f)
                verticalLineToRelative(-4.25f)
                horizontalLineToRelative(-0.958f)
                quadToRelative(-0.292f, 0f, -0.521f, -0.229f)
                quadToRelative(-0.229f, -0.23f, -0.229f, -0.521f)
                quadToRelative(0f, -0.292f, 0.229f, -0.521f)
                quadToRelative(0.229f, -0.229f, 0.521f, -0.229f)
                horizontalLineToRelative(1.708f)
                quadToRelative(0.292f, 0f, 0.5f, 0.229f)
                quadToRelative(0.208f, 0.229f, 0.208f, 0.521f)
                verticalLineToRelative(5f)
                quadToRelative(0f, 0.291f, -0.208f, 0.521f)
                quadToRelative(-0.208f, 0.229f, -0.5f, 0.229f)
                close()
                moveToRelative(-1.708f, 9.916f)
                quadToRelative(-0.292f, 0f, -0.521f, -0.229f)
                quadToRelative(-0.229f, -0.229f, -0.229f, -0.521f)
                verticalLineToRelative(-0.375f)
                quadToRelative(0f, -0.041f, 0.208f, -0.458f)
                lineTo(8.25f, 18.25f)
                horizontalLineTo(5.917f)
                quadToRelative(-0.292f, 0f, -0.521f, -0.229f)
                quadToRelative(-0.229f, -0.229f, -0.229f, -0.479f)
                quadToRelative(0f, -0.292f, 0.229f, -0.521f)
                quadToRelative(0.229f, -0.229f, 0.521f, -0.229f)
                horizontalLineToRelative(3.375f)
                quadToRelative(0.291f, 0f, 0.52f, 0.229f)
                quadToRelative(0.23f, 0.229f, 0.23f, 0.521f)
                verticalLineToRelative(0.375f)
                quadToRelative(0f, 0.041f, -0.167f, 0.458f)
                lineTo(6.958f, 21.75f)
                horizontalLineToRelative(2.375f)
                quadToRelative(0.292f, 0f, 0.5f, 0.229f)
                quadToRelative(0.209f, 0.229f, 0.209f, 0.479f)
                quadToRelative(0f, 0.292f, -0.209f, 0.521f)
                quadToRelative(-0.208f, 0.229f, -0.5f, 0.229f)
                close()
                moveToRelative(9.125f, 8f)
                quadToRelative(-0.542f, 0f, -0.917f, -0.375f)
                reflectiveQuadToRelative(-0.375f, -0.958f)
                quadToRelative(0f, -0.542f, 0.375f, -0.917f)
                reflectiveQuadToRelative(0.917f, -0.375f)
                horizontalLineTo(33.5f)
                quadToRelative(0.542f, 0f, 0.938f, 0.396f)
                quadToRelative(0.395f, 0.396f, 0.395f, 0.938f)
                quadToRelative(0f, 0.541f, -0.395f, 0.916f)
                quadToRelative(-0.396f, 0.375f, -0.938f, 0.375f)
                close()
                moveToRelative(0f, -9.958f)
                quadToRelative(-0.542f, 0f, -0.917f, -0.396f)
                reflectiveQuadToRelative(-0.375f, -0.937f)
                quadToRelative(0f, -0.542f, 0.375f, -0.917f)
                reflectiveQuadToRelative(0.917f, -0.375f)
                horizontalLineTo(33.5f)
                quadToRelative(0.542f, 0f, 0.938f, 0.375f)
                quadToRelative(0.395f, 0.375f, 0.395f, 0.917f)
                quadToRelative(0f, 0.583f, -0.395f, 0.958f)
                quadToRelative(-0.396f, 0.375f, -0.938f, 0.375f)
                close()
                moveToRelative(0f, -9.958f)
                quadToRelative(-0.542f, 0f, -0.917f, -0.396f)
                reflectiveQuadToRelative(-0.375f, -0.938f)
                quadToRelative(0f, -0.541f, 0.375f, -0.937f)
                reflectiveQuadToRelative(0.917f, -0.396f)
                horizontalLineTo(33.5f)
                quadToRelative(0.542f, 0f, 0.938f, 0.396f)
                quadToRelative(0.395f, 0.396f, 0.395f, 0.937f)
                quadToRelative(0f, 0.542f, -0.395f, 0.938f)
                quadToRelative(-0.396f, 0.396f, -0.938f, 0.396f)
                close()
            }
        }.build()
    }
}