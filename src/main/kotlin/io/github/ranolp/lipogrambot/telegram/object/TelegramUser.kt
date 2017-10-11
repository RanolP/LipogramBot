package io.github.ranolp.lipogrambot.telegram.`object`

import com.github.salomonbrys.kotson.byLong
import com.github.salomonbrys.kotson.byNullableString
import com.github.salomonbrys.kotson.byString
import com.google.gson.JsonObject
import io.github.ranolp.lipogrambot.layer.LipogramUser
import io.github.ranolp.lipogrambot.telegram.Telegram

class TelegramUser(jsonObject: JsonObject) : LipogramUser() {
    override val id by jsonObject.byLong
    private val firstName by jsonObject.byString("first_name")
    private val lastName by jsonObject.byNullableString("last_name")
    val username by jsonObject.byString
    override val name: String
        get() = (firstName + " " + (lastName ?: "")).trimEnd()
    override val platform = Telegram.PLATFORM
}