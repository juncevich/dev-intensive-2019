package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        val firstName: String?
        val lastName: String?

        when (fullName) {
            null, "", " " -> {
                firstName = null
                lastName = null
            }
            else -> {
                firstName = parts?.getOrNull(0)
                lastName = parts?.getOrNull(1)
            }
        }



        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if (firstName == null && lastName == null){
            return null
        }
        if ((firstName == "" || firstName == " ") && (lastName == "" || lastName == " ")) {
            return null
        }
        return "${if (firstName == null) "" else "${firstName[0].toUpperCase()}"}${if (firstName == null || lastName == null) "" else " "}${if (lastName == null) "" else "${lastName[0].toUpperCase()}"}"
    }


}