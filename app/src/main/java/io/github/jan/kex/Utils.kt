package io.github.jan.kex

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import java.time.format.TextStyle
import java.util.Locale

data class StringResource(@StringRes val id: Int, val args: List<Any> = emptyList())

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
    }" + if (year != Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year) " $year" else ""

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

val Int.localizedDay
    get() = when(this) {
        0 -> R.string.today
        1 -> R.string.tomorrow
        2 -> R.string.day_after_tomorrow
        else -> R.string.in_days
    }

fun Instant.toDate() = this.toLocalDateTime(TimeZone.currentSystemDefault()).date

fun LocalDate.toInstant(): Instant = LocalDateTime(this, LocalTime(0,0)).toInstant(TimeZone.UTC)