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
fun rememberDownloadIcon(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "download",
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
                moveTo(20f, 26.25f)
                quadToRelative(-0.25f, 0f, -0.479f, -0.083f)
                quadToRelative(-0.229f, -0.084f, -0.438f, -0.292f)
                lineToRelative(-6.041f, -6.083f)
                quadToRelative(-0.417f, -0.375f, -0.396f, -0.917f)
                quadToRelative(0.021f, -0.542f, 0.396f, -0.917f)
                reflectiveQuadToRelative(0.916f, -0.396f)
                quadToRelative(0.542f, -0.02f, 0.959f, 0.396f)
                lineToRelative(3.791f, 3.792f)
                verticalLineTo(8.292f)
                quadToRelative(0f, -0.584f, 0.375f, -0.959f)
                reflectiveQuadTo(20f, 6.958f)
                quadToRelative(0.542f, 0f, 0.938f, 0.375f)
                quadToRelative(0.395f, 0.375f, 0.395f, 0.959f)
                verticalLineTo(21.75f)
                lineToRelative(3.792f, -3.792f)
                quadToRelative(0.375f, -0.416f, 0.917f, -0.396f)
                quadToRelative(0.541f, 0.021f, 0.958f, 0.396f)
                quadToRelative(0.375f, 0.375f, 0.375f, 0.917f)
                reflectiveQuadToRelative(-0.375f, 0.958f)
                lineToRelative(-6.083f, 6.042f)
                quadToRelative(-0.209f, 0.208f, -0.438f, 0.292f)
                quadToRelative(-0.229f, 0.083f, -0.479f, 0.083f)
                close()
                moveTo(9.542f, 32.958f)
                quadToRelative(-1.042f, 0f, -1.834f, -0.791f)
                quadToRelative(-0.791f, -0.792f, -0.791f, -1.834f)
                verticalLineToRelative(-4.291f)
                quadToRelative(0f, -0.542f, 0.395f, -0.938f)
                quadToRelative(0.396f, -0.396f, 0.938f, -0.396f)
                quadToRelative(0.542f, 0f, 0.917f, 0.396f)
                reflectiveQuadToRelative(0.375f, 0.938f)
                verticalLineToRelative(4.291f)
                horizontalLineToRelative(20.916f)
                verticalLineToRelative(-4.291f)
                quadToRelative(0f, -0.542f, 0.375f, -0.938f)
                quadToRelative(0.375f, -0.396f, 0.917f, -0.396f)
                quadToRelative(0.583f, 0f, 0.958f, 0.396f)
                reflectiveQuadToRelative(0.375f, 0.938f)
                verticalLineToRelative(4.291f)
                quadToRelative(0f, 1.042f, -0.791f, 1.834f)
                quadToRelative(-0.792f, 0.791f, -1.834f, 0.791f)
                close()
            }
        }.build()
    }
}