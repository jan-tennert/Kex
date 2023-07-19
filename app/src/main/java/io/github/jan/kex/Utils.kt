package io.github.jan.kex

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.Modifier
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.shimmer
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.format.TextStyle
import java.util.Locale

val LocalDate.localizedDateString
    @RequiresApi(Build.VERSION_CODES.O)
    get() = dayOfWeek.getDisplayName(
        TextStyle.SHORT,
        Locale.getDefault()
    ) + " $dayOfMonth ${
        month.getDisplayName(
            TextStyle.FULL,
            Locale.getDefault()
        )
    }"

val LocalDateTime.localizedDateString
    @RequiresApi(Build.VERSION_CODES.O)
    get() = dayOfWeek.getDisplayName(
        TextStyle.SHORT,
        Locale.getDefault()
    ) + " $dayOfMonth ${
        month.getDisplayName(
            TextStyle.FULL,
            Locale.getDefault()
        )
    }"

val Instant.localizedDateString
    @RequiresApi(Build.VERSION_CODES.O)
    get() = toLocalDateTime(TimeZone.currentSystemDefault()).localizedDateString

fun Modifier.shimmerIf(shimmer: Boolean, instance: Shimmer) = if (shimmer) shimmer(instance) else this