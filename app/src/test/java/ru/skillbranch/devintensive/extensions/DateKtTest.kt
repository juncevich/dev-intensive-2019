package ru.skillbranch.devintensive.extensions

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class DateKtTest{
    @Test
    fun test_humanize() {
        var diffMessage = Date().add(-2, TimeUnits.YEAR).humanizeDiff()
        assertEquals("Более 2 лет назад", diffMessage)

        diffMessage = Date().add(2, TimeUnits.YEAR).humanizeDiff()
        assertEquals("Более 2 лет вперед", diffMessage)
    }
}