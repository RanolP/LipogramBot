@file:JvmName("Main")

package io.github.ranolp.lipogrambot

import com.github.salomonbrys.kotson.obj
import io.github.ranolp.lipogrambot.core.command.CommandManager
import io.github.ranolp.lipogrambot.general.commands.LipogramCommand
import io.github.ranolp.lipogrambot.general.options.CommandOption
import io.github.ranolp.lipogrambot.general.options.Option
import io.github.ranolp.lipogrambot.general.options.TelegramOption
import io.github.ranolp.lipogrambot.telegram.TelegramBot
import io.github.ranolp.waffle.log.Logger
import io.github.ranolp.waffle.log.Text
import io.github.ranolp.waffle.log.TextModifier
import io.github.ranolp.waffle.log.color
import io.github.ranolp.waffle.util.Json

fun main(args: Array<String>) {
    try {
        Option.INSTANCE = Option(Json.parseFile("resources/setting.json").obj)
        Logger.info("Log level : " + Logger.logLevel.name)
        if (TelegramOption.use) {
            Logger.info("Telegram bot enabled".color(TextModifier.UNDERLINE, text = Text.GREEN))
            TelegramBot.start()
        }
        if(CommandOption.lipogram) {
            Logger.info("Register command : /lipogram")
            CommandManager += LipogramCommand
        }
    } catch (t: Throwable) {
        t.printStackTrace()
        println("Press Enter key to exit.")
        readLine()
        System.exit(0)
    }
}