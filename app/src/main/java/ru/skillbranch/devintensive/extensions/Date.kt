package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

private const val SECOND = 1000L
private const val MINUTE = 60 * SECOND
private const val HOUR = 60 * MINUTE
private const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, timeUnit: TimeUnits): Date {
    return this.apply {
        time += when (timeUnit) {
            TimeUnits.SECOND -> value * SECOND
            TimeUnits.DAY -> value * DAY
            TimeUnits.HOUR -> value * HOUR
            TimeUnits.MINUTE -> value * MINUTE
        }
    }

}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diff = date.time - this.time
    val absDiff = Math.abs(diff)
    val isPast = diff > 0
    return when {
        absDiff / SECOND <= 1 -> "только что"
        absDiff / SECOND <= 45 -> if (diff > 0) "несколько секунд назад" else "через несколько секунд"
        absDiff / SECOND <= 75 -> if (diff > 0) "минуту назад" else "через минуту"
        absDiff / MINUTE <= 45 -> plurals(absDiff / MINUTE, isPast, TimeUnits.MINUTE)
        absDiff / MINUTE <= 75 -> if (diff > 0) "час назад" else "через час"
        absDiff / HOUR <= 22 -> plurals(absDiff / HOUR, isPast, TimeUnits.HOUR)
        absDiff / HOUR <= 26 -> if (diff > 0) "день назад" else "через день"
        absDiff / DAY <= 360 -> plurals(absDiff / DAY, isPast, TimeUnits.DAY)
        else -> if (diff > 0) "более года назад" else "более чем через год"
    }
}


private fun plurals(diff: Long, isPast: Boolean, units: TimeUnits): String {
    val remainder = diff % 10
    val quotient = diff / 10
    val preLastDigit = diff % 100 / 10

    val plurals: Map<TimeUnits, Map<PluralUnits, String>> = mapOf(
        TimeUnits.MINUTE to mapOf(
            PluralUnits.FEW to "минуты",
            PluralUnits.ONE to "минуту",
            PluralUnits.MANY to "минут"
        ),
        TimeUnits.HOUR to mapOf(
            PluralUnits.FEW to "часа",
            PluralUnits.ONE to "час",
            PluralUnits.MANY to "часов"
        ),
        TimeUnits.DAY to mapOf(
            PluralUnits.FEW to "дня",
            PluralUnits.ONE to "день",
            PluralUnits.MANY to "дней"
        )
    )

    return when {
        (preLastDigit == 1L) -> if (isPast) "$diff ${plurals[units]?.get(PluralUnits.MANY)} назад"
        else "через $diff ${plurals[units]?.get(PluralUnits.MANY)}"

        (remainder in 2..4) && (quotient != 1L) ->
            if (isPast) "$diff ${plurals[units]?.get(PluralUnits.FEW)} назад"
            else "через $diff ${plurals[units]?.get(PluralUnits.FEW)}"
        (remainder == 1L) && (quotient != 1L) ->
            if (isPast) "$diff ${plurals[units]?.get(PluralUnits.ONE)} назад"
            else "через $diff ${plurals[units]?.get(PluralUnits.ONE)}"
        else ->
            if (isPast) "$diff ${plurals[units]?.get(PluralUnits.MANY)} назад"
            else "через $diff ${plurals[units]?.get(PluralUnits.MANY)}"
    }


}

enum class PluralUnits {
    FEW, ONE, MANY
}

enum class TimeUnits { SECOND, MINUTE, HOUR, DAY }