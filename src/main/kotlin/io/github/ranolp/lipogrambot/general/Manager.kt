package io.github.ranolp.lipogrambot.general

import io.github.ranolp.lipogrambot.core.command.CommandManager
import io.github.ranolp.lipogrambot.core.layer.LipogramGroup
import io.github.ranolp.lipogrambot.core.layer.LipogramUser

object Manager {
    fun messageReceived(from: LipogramUser, group: LipogramGroup, message: String) {
        if (from.useLipogram) {
            val result = group.lipogram.test(message) + from.lipogram.test(message)
            val found = result.filteredWords.joinToString(" 있고,\n") {
                "'${it.word}'에 '" + it.chars.dropLast(1).joinToString("") { it + ", " } + it.chars.last().let { it.toString() + "'" + if ((it.toInt() - 0xAC00) % 28 == 0) "가" else "이" }
            }
            if (result.count > 0) {
                group.sendMessage("지금 걸리셨어요, ${from.name}님!\n$found 있어요!")
            }
        }
    }

    fun commandReceived(from: LipogramUser, group: LipogramGroup, label: String, arguments: List<String>) {
        CommandManager.dispatchCommand(group, from, label, arguments)
    }
}