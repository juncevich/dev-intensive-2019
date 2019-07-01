package ru.skillbranch.devintensive.model

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var raiting: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false
) {
    var introBit: String = "tu tu tu"

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(id, "John", "Doe")

    init {
        introBit = getIntro()
        println(
            "It's Alive !!! " +
                    "\n${if (lastName === "Doe") "His name $firstName $lastName" else "And his name $firstName $lastName"}\n" +
                    "${getIntro()}"
        )
    }

    private fun getIntro() = """
        tu tu tu
        ${"\n"}
        $firstName $lastName
    """.trimIndent()

    fun printMe() = println(
        """
        id:$id
        firstName:$firstName
        lastName:$lastName
        avatar:$avatar
        raiting:$raiting
        respect:$respect
        lastVisit:$lastVisit
        isOnline:$isOnline
        """.trimIndent()
    )

    companion object Factory {
        private var lastId = -1
        fun makeUser(fullName: String?): User {
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }
}