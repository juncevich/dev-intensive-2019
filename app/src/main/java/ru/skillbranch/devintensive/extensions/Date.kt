package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR
const val YEAR = 365 * DAY

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
        TimeUnits.YEAR -> value * YEAR
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {

    val diff = this.time - date.time
    val diffInYears = diff / YEAR
    if (diffInYears != 0L) {
        val absDiff = abs(diffInYears)
        if (diff > 0) {
            return "Более $absDiff лет вперед"
        } else {
            return "Более $absDiff лет назад"
        }
    }
    return "empty"
}

enum class TimeUnits {
    SECOND, MINUTE, HOUR, DAY, YEAR
}