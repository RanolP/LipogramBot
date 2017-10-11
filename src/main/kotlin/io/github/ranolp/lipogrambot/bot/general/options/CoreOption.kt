package io.github.ranolp.lipogrambot.general.options

import com.github.salomonbrys.kotson.byString
import com.google.gson.JsonObject

class CoreOption internal constructor(jsonObject: JsonObject) {
    companion object {
        private val INSTANCE: CoreOption
            get() = Option.core

        val logLevel: String
            get() = INSTANCE._logLevel
        val dateSyntax: String
            get() = INSTANCE._dateSyntax
    }

    private val _logLevel by jsonObject.byString("log-level") { "info" }
    private val _dateSyntax by jsonObject.byString("date-syntax") { "hh:mm:ss" }
}