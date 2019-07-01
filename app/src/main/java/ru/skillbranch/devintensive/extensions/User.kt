package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.model.User
import ru.skillbranch.devintensive.model.UserView
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

fun User.toUserView(): UserView {

    val nickName = Utils.transliteration("$firstName $lastVisit")
    val initials = Utils.toInitials(firstName, lastName)
    val status =
        if (lastVisit == null) "Еще ни разу не был" else if (isOnline) "online" else "Последний раз был ${lastVisit.humanizeDiff()}"
    return UserView(
        id,
        fullName = "$firstName $lastName",
        nickName = nickName,
        initials = avatar,
        status = status
    )
}


