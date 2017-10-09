package io.github.ranolp.lipogrambot.telegram

import io.github.ranolp.lipogrambot.telegram.receiver.LongPollingReceiver
import kotlin.concurrent.thread

object TelegramBot {
    fun start() {
        thread(start = true, name = "TelegramLongPollingReceiver") {
            LongPollingReceiver.listen()
        }
    }
}