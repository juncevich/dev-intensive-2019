package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> = when {
        fullName == null -> null to null
        fullName.trim().isEmpty() -> null to null
        else -> fullName.trim().replace(Regex(" +"), " ").split(" ").let {
            it.firstOrNull() to it.getOrNull(1)
        }
    }

    fun transliteration(payload: String, divider: String = " "): String = buildString {
        payload.asSequence().forEach {
            append(if (it == ' ') divider else it.transliterate())
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var initials: String? = ""
        for (part: String? in arrayOf(firstName, lastName)) {
            initials += when {
                part == null -> ""
                part.trim() == "" -> ""
                else -> part.substring(0, 1).toUpperCase()
            }
        }
        initials = if (initials == "") null else initials
        return initials
    }
}

private val chars = mapOf(
        'а' to "a",
        'б' to "b",
        'в' to "v",
        'г' to "g",
        'д' to "d",
        'е' to "e",
        'ё' to "e",
        'ж' to "zh",
        'з' to "z",
        'и' to "i",
        'й' to "i",
        'к' to "k",
        'л' to "l",
        'м' to "m",
        'н' to "n",
        'о' to "o",
        'п' to "p",
        'р' to "r",
        'с' to "s",
        'т' to "t",
        'у' to "u",
        'ф' to "f",
        'х' to "h",
        'ц' to "c",
        'ч' to "ch",
        'ш' to "sh",
        'щ' to "sh'",
        'ъ' to "",
        'ы' to "i",
        'ь' to "",
        'э' to "e",
        'ю' to "yu",
        'я' to "ya")

private fun Char.transliterate(): String {
    return if (isUpperCase()) {
        chars[this.toLowerCase()]?.toUpperCase() ?: this.toString()
    } else {
        chars[this] ?: this.toString()
    }
}