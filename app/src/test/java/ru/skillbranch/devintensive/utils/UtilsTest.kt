package ru.skillbranch.devintensive.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTest {

    @Test
    internal fun test_initials() {
        var toInitials = Utils.toInitials(null, null)
        assertEquals(null, toInitials)

        toInitials = Utils.toInitials("Alex", "Wayne")
        assertEquals("A W", toInitials)

        toInitials = Utils.toInitials("Alex", null)
        assertEquals("A", toInitials)

        toInitials = Utils.toInitials(null, "Wayne")
        assertEquals("W", toInitials)

        toInitials = Utils.toInitials("", " ")
        assertEquals(null, toInitials)

    }

    @Test
    fun test_parse_full_name() {
        val (firstName, lastName) = Utils.parseFullName("Alex Wayne")
        assertEquals(firstName, "Alex")
        assertEquals(lastName, "Wayne")

        val (firstName1, lastName1) = Utils.parseFullName("Alex")
        assertEquals(firstName1, "Alex")
        assertEquals(lastName1, null)

        val (firstName2, lastName2) = Utils.parseFullName("")
        assertEquals(firstName2, null)
        assertEquals(lastName2, null)

        val (firstName3, lastName3) = Utils.parseFullName(" ")
        assertEquals(firstName3, null)
        assertEquals(lastName3, null)

        val (firstName4, lastName4) = Utils.parseFullName(null)
        assertEquals(firstName4, null)
        assertEquals(lastName4, null)
    }
}

