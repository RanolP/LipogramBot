package io.github.ranolp.lipogrambot.telegram.`object`

import com.github.salomonbrys.kotson.byLong
import com.github.salomonbrys.kotson.byNullableString
import com.google.gson.JsonObject
import io.github.ranolp.lipogrambot.core.layer.LipogramGroup
import io.github.ranolp.lipogrambot.core.layer.LipogramUser
import io.github.ranolp.lipogrambot.telegram.Telegram

class TelegramGroup(jsonObject: JsonObject) : LipogramGroup() {
    override val id by jsonObject.byLong
    override val users: List<LipogramUser>
        get() = emptyList()
    private val title by jsonObject.byNullableString("title")
    private val firstName by jsonObject.byNullableString("first_name")
    private val lastName by jsonObject.byNullableString("last_name")
    override val platform = Telegram.PLATFORM
    override val name: String
        get() = title ?: if (firstName != null) (firstName + " " + (lastName ?: "")).trimEnd() else "Unnamed"

    override fun sendMessage(message: String) {
        Telegram.sendMessage(this, message)
    }
}