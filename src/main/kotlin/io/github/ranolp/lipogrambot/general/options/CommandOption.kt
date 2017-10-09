package io.github.ranolp.lipogrambot.general.options

import com.github.salomonbrys.kotson.byBool
import com.google.gson.JsonObject

class CommandOption internal constructor(jsonObject: JsonObject) {
    companion object {
        private val INSTANCE: CommandOption
            get() = Option.command

        val lipogram: Boolean
            get() = INSTANCE._lipogram
    }

    private val _lipogram by jsonObject.byBool("lipogram") { true }
}