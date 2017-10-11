package io.github.ranolp.lipogrambot.bot.command

import io.github.ranolp.lipogrambot.layer.LipogramGroup
import io.github.ranolp.lipogrambot.layer.LipogramUser
import io.github.ranolp.lipogrambot.layer.Permission

interface Command {
    val aliases: List<String>
    val permissions: List<Permission>

    fun run(group: LipogramGroup, runner: LipogramUser, label: String, arguments: List<String>)
}