package io.github.ranolp.lipogrambot.telegram.`object`

import com.github.salomonbrys.kotson.byLong
import com.google.gson.JsonObject
import io.github.ranolp.waffle.util.byNullableClass

/*
 * Subset of Telegram's update json structure
 * Original: https://core.telegram.org/bots/api#update
 */
class Update(jsonObject: JsonObject) {
    val id by jsonObject.byLong("update_id")
    val message by jsonObject.byNullableClass(init = ::TelegramMessage)
    val editedMessage by jsonObject.byNullableClass("edited_message", ::TelegramMessage)
}