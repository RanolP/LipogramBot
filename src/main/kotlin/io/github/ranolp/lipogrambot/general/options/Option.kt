package io.github.ranolp.lipogrambot.general.options

import com.google.gson.JsonObject
import io.github.ranolp.waffle.util.byClass

class Option(jsonObject: JsonObject) {
    companion object {
        internal lateinit var INSTANCE: Option

        val core: CoreOption
            get() = INSTANCE._core
        val telegram: TelegramOption
            get() = INSTANCE._telegram
        val command: CommandOption
            get() = INSTANCE._command
    }

    private val _core by jsonObject.byClass("core", ::CoreOption)
    private val _command by jsonObject.byClass("command", ::CommandOption)
    private val _telegram by jsonObject.byClass("telegram", ::TelegramOption)
}