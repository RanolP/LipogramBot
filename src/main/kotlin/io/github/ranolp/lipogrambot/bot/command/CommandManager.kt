package io.github.ranolp.lipogrambot.core.command

import io.github.ranolp.lipogrambot.bot.command.Command
import io.github.ranolp.lipogrambot.layer.LipogramGroup
import io.github.ranolp.lipogrambot.layer.LipogramUser

object CommandManager {
    private val commands = mutableMapOf<String, Command>()

    @JvmName("registerCommand") operator fun plusAssign(command: Command) {
        command.aliases.forEach {
            commands += it to command
        }
    }

    @JvmName("unregisterCommand") operator fun minusAssign(label: String) {
        commands -= label
    }

    @JvmName("unregisterCommand") operator fun minusAssign(command: Command) {
        commands -= command.aliases
    }

    fun dispatchCommand(group: LipogramGroup, runner: LipogramUser, label: String, arguments: List<String>) {
        commands[label]?.run(group, runner, label, arguments)
    }
}