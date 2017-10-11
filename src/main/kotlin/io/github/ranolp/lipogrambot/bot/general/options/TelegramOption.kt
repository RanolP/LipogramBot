package io.github.ranolp.lipogrambot.general.options

import com.github.salomonbrys.kotson.byBool
import com.github.salomonbrys.kotson.byString
import com.google.gson.JsonObject
import io.github.ranolp.lipogrambot.telegram.receiver.MessageReceiverType

class TelegramOption internal constructor(jsonObject: JsonObject) {
    companion object {
        private val INSTANCE: TelegramOption
            get() = Option.telegram

        val use: Boolean
            get() = INSTANCE._use
        val botToken: String
            get() = INSTANCE._botToken
        val messageReceiverType: MessageReceiverType
            get() = MessageReceiverType.values().firstOrNull { it.name == INSTANCE._messageReceiverType.toUpperCase() } ?: MessageReceiverType.LONG_POLLING
    }

    private val _use by jsonObject.byBool("use") { true }
    private val _botToken by jsonObject.byString("bot-token")
    private val _messageReceiverType by jsonObject.byString("message-receiver-type") { "LONG_POLLING" }
}