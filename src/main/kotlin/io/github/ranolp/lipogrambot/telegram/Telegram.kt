package io.github.ranolp.lipogrambot.telegram

import com.github.kittinunf.fuel.httpGet
import com.github.salomonbrys.kotson.jsonObject
import com.github.salomonbrys.kotson.nullArray
import com.github.salomonbrys.kotson.nullBool
import com.github.salomonbrys.kotson.obj
import com.google.gson.JsonObject
import io.github.ranolp.lipogrambot.core.layer.Platform
import io.github.ranolp.lipogrambot.core.storage.GroupStorage
import io.github.ranolp.lipogrambot.core.storage.UserStorage
import io.github.ranolp.lipogrambot.general.options.TelegramOption
import io.github.ranolp.lipogrambot.telegram.`object`.TelegramGroup
import io.github.ranolp.lipogrambot.telegram.`object`.TelegramUser
import io.github.ranolp.lipogrambot.telegram.`object`.Update
import io.github.ranolp.waffle.log.Logger
import io.github.ranolp.waffle.util.Json

object Telegram {
    val PLATFORM = Platform("Telegram")
    val USER_STORAGE = UserStorage<TelegramUser>()
    val GROUP_STORAGE = GroupStorage<TelegramGroup>()
    fun callFunction(name: String, params: Map<String, Any?> = emptyMap()): JsonObject {
        return try {
            Json.parse("https://api.telegram.org/bot${TelegramOption.botToken}/$name".httpGet(params.toList()).responseString().third.get()).obj
        } catch (th: Throwable) {
            Logger.error(th)
            jsonObject("ok" to false)
        }
    }

    // Minimize for long polling
    fun getUpdates(offset: Long? = null): List<Update> {
        callFunction("getUpdates", mapOf("offset" to offset)).let {
            return if (it["ok"].nullBool == true) {
                it["result"].nullArray?.mapNotNull { if (it.isJsonObject) Update(it.obj) else null } ?: emptyList()
            } else {
                emptyList()
            }
        }
    }

    fun getMe() = callFunction("getMe").let { if (it["ok"].nullBool == true) TelegramUser(it["result"].obj) else null }

    fun sendMessage(to: TelegramGroup, text: String) {
        callFunction("sendMessage", mapOf("chat_id" to to.id, "text" to text))
    }
}