package io.github.ranolp.lipogrambot.telegram.receiver

import io.github.ranolp.lipogrambot.core.layer.LipogramGroup
import io.github.ranolp.lipogrambot.core.layer.LipogramUser
import io.github.ranolp.lipogrambot.general.Manager
import io.github.ranolp.lipogrambot.telegram.Telegram
import io.github.ranolp.waffle.log.Logger

object LongPollingReceiver {
    private val me = Telegram.getMe()
    fun listen() {
        Logger.info("Start telegram long-polling receiver")
        var currentMaxId: Long = 0
        outer@ while (true) {
            val updates = if (currentMaxId > 0) {
                Telegram.getUpdates(currentMaxId)
            } else {
                Telegram.getUpdates()
            }
            if (updates.isEmpty()) continue
            for (update in updates) {
                update.message?.let {
                    if (it.isValid) {
                        val from = it.from!!
                        val group = it.chat
                        it.caption?.let {
                            received(from, group, it)
                        }
                        it.text?.let {
                            received(from, group, it)
                        }
                    }
                }
                currentMaxId = maxOf(currentMaxId, update.id + 1)
            }
        }
    }

    private fun received(from: LipogramUser, group: LipogramGroup, message: String) {
        if (message[0] == '/') {
            val space = message.indexOf(' ')
            var label = if (space == -1) message.substring(1) else message.substring(1, space)
            val at = label.indexOf('@')
            if (at != -1) {
                val me = this.me
                if (me != null && label.substring(at + 1) != me.username) {
                    return
                }
                label = label.substring(0, at)
            }
            val arguments = if (space == -1) emptyList() else message.substring(space + 1).split(' ')
            Manager.commandReceived(from, group, label, arguments)
        } else {
            Manager.messageReceived(from, group, message)
        }
    }
}