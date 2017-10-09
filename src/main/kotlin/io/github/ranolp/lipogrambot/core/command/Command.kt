package io.github.ranolp.lipogrambot.core.command

import io.github.ranolp.lipogrambot.core.layer.LipogramGroup
import io.github.ranolp.lipogrambot.core.layer.LipogramUser
import io.github.ranolp.lipogrambot.core.layer.Permission

interface Command {
    val aliases: List<String>
    val permissions: List<Permission>

    fun run(group: LipogramGroup, runner: LipogramUser, label: String, arguments: List<String>)
}