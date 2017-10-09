package io.github.ranolp.lipogrambot.telegram.`object`

import com.github.salomonbrys.kotson.*
import com.google.gson.JsonObject
import io.github.ranolp.lipogrambot.telegram.Telegram
import io.github.ranolp.waffle.util.byClass
import io.github.ranolp.waffle.util.byNullableClass

class TelegramMessage(jsonObject: JsonObject) {
    val from by jsonObject.byNullableClass(init = {
        val newFrom = TelegramUser(it)
        Telegram.USER_STORAGE[newFrom.id] = newFrom
        newFrom
    })
    val chat by jsonObject.byClass(init = {
        val newGroup = TelegramGroup(it)
        Telegram.GROUP_STORAGE[newGroup.id] = newGroup
        newGroup
    })
    val date by jsonObject.byLong
    private val _forwardFromId by jsonObject.byNullableLong("forward_from_message_id")
    val text by jsonObject.byNullableString
    val caption by jsonObject.byNullableString
    private val _newChatMembers by jsonObject.byNullableArray("new_chat_members")
    private val _leftChatMember by jsonObject.byNullableObject("left_chat_member")

    val isValid: Boolean
        get() = System.currentTimeMillis() - date * 1000 < 10000 && (text !== null || caption !== null) && from !== null && _forwardFromId === null && _newChatMembers === null && _leftChatMember === null
}