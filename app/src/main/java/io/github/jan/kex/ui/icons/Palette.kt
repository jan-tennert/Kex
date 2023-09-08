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
fun rememberPaletteIcon(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "palette",
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
                moveTo(20f, 36.375f)
                quadToRelative(-3.375f, 0f, -6.354f, -1.292f)
                quadToRelative(-2.979f, -1.291f, -5.208f, -3.521f)
                quadToRelative(-2.23f, -2.229f, -3.521f, -5.208f)
                quadTo(3.625f, 23.375f, 3.625f, 20f)
                quadToRelative(0f, -3.458f, 1.313f, -6.458f)
                quadToRelative(1.312f, -3f, 3.583f, -5.209f)
                quadToRelative(2.271f, -2.208f, 5.312f, -3.458f)
                quadToRelative(3.042f, -1.25f, 6.5f, -1.25f)
                quadToRelative(3.292f, 0f, 6.188f, 1.104f)
                reflectiveQuadToRelative(5.083f, 3.042f)
                quadToRelative(2.188f, 1.937f, 3.479f, 4.604f)
                quadToRelative(1.292f, 2.667f, 1.292f, 5.792f)
                quadToRelative(0f, 4.5f, -2.646f, 7.041f)
                quadToRelative(-2.646f, 2.542f, -6.854f, 2.542f)
                horizontalLineToRelative(-3.083f)
                quadToRelative(-0.667f, 0f, -1.104f, 0.479f)
                quadToRelative(-0.438f, 0.479f, -0.438f, 1.063f)
                quadToRelative(0f, 0.958f, 0.604f, 1.854f)
                quadToRelative(0.604f, 0.896f, 0.604f, 1.979f)
                quadToRelative(0f, 1.542f, -0.875f, 2.396f)
                quadToRelative(-0.875f, 0.854f, -2.583f, 0.854f)
                close()
                moveTo(20f, 20f)
                close()
                moveToRelative(-9.458f, 1.208f)
                quadToRelative(0.916f, 0f, 1.541f, -0.625f)
                quadToRelative(0.625f, -0.625f, 0.625f, -1.541f)
                quadToRelative(0f, -0.875f, -0.625f, -1.5f)
                reflectiveQuadToRelative(-1.541f, -0.625f)
                quadToRelative(-0.875f, 0f, -1.5f, 0.625f)
                reflectiveQuadToRelative(-0.625f, 1.5f)
                quadToRelative(0f, 0.916f, 0.625f, 1.541f)
                quadToRelative(0.625f, 0.625f, 1.5f, 0.625f)
                close()
                moveToRelative(5.166f, -6.916f)
                quadToRelative(0.875f, 0f, 1.5f, -0.625f)
                reflectiveQuadToRelative(0.625f, -1.5f)
                quadToRelative(0f, -0.875f, -0.625f, -1.5f)
                reflectiveQuadToRelative(-1.5f, -0.625f)
                quadToRelative(-0.916f, 0f, -1.541f, 0.625f)
                quadToRelative(-0.625f, 0.625f, -0.625f, 1.5f)
                reflectiveQuadToRelative(0.625f, 1.5f)
                quadToRelative(0.625f, 0.625f, 1.541f, 0.625f)
                close()
                moveToRelative(8.625f, 0f)
                quadToRelative(0.875f, 0f, 1.5f, -0.625f)
                reflectiveQuadToRelative(0.625f, -1.5f)
                quadToRelative(0f, -0.875f, -0.625f, -1.5f)
                reflectiveQuadToRelative(-1.5f, -0.625f)
                quadToRelative(-0.875f, 0f, -1.5f, 0.625f)
                reflectiveQuadToRelative(-0.625f, 1.5f)
                quadToRelative(0f, 0.875f, 0.625f, 1.5f)
                reflectiveQuadToRelative(1.5f, 0.625f)
                close()
                moveToRelative(5.25f, 6.916f)
                quadToRelative(0.875f, 0f, 1.5f, -0.625f)
                reflectiveQuadToRelative(0.625f, -1.541f)
                quadToRelative(0f, -0.875f, -0.625f, -1.5f)
                reflectiveQuadToRelative(-1.5f, -0.625f)
                quadToRelative(-0.875f, 0f, -1.5f, 0.625f)
                reflectiveQuadToRelative(-0.625f, 1.5f)
                quadToRelative(0f, 0.916f, 0.625f, 1.541f)
                quadToRelative(0.625f, 0.625f, 1.5f, 0.625f)
                close()
                moveTo(20f, 33.75f)
                quadToRelative(0.417f, 0f, 0.625f, -0.188f)
                quadToRelative(0.208f, -0.187f, 0.208f, -0.604f)
                quadToRelative(0f, -0.583f, -0.604f, -1.187f)
                quadToRelative(-0.604f, -0.604f, -0.604f, -2.229f)
                quadToRelative(0f, -1.834f, 1.229f, -3.125f)
                quadToRelative(1.229f, -1.292f, 3.063f, -1.292f)
                horizontalLineToRelative(2.958f)
                quadToRelative(3f, 0f, 4.937f, -1.771f)
                quadToRelative(1.938f, -1.771f, 1.938f, -5.187f)
                quadToRelative(0f, -5.292f, -4.021f, -8.605f)
                quadToRelative(-4.021f, -3.312f, -9.354f, -3.312f)
                quadToRelative(-5.917f, 0f, -10.021f, 4f)
                quadToRelative(-4.104f, 4f, -4.104f, 9.75f)
                quadToRelative(0f, 5.708f, 4.021f, 9.729f)
                quadTo(14.292f, 33.75f, 20f, 33.75f)
                close()
            }
        }.build()
    }
}