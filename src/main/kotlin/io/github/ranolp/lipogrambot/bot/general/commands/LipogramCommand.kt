package io.github.ranolp.lipogrambot.general.commands

import io.github.ranolp.core.Project
import io.github.ranolp.lipogrambot.bot.command.Command
import io.github.ranolp.lipogrambot.layer.LipogramGroup
import io.github.ranolp.lipogrambot.layer.LipogramUser
import io.github.ranolp.lipogrambot.layer.Permission

object LipogramCommand : Command {
    override val aliases = listOf("lipogram")
    override val permissions = emptyList<Permission>()

    override fun run(group: LipogramGroup, runner: LipogramUser, label: String, arguments: List<String>) {
        if (arguments.isEmpty()) {
            group.sendMessage("리포그램 봇 ${Project.VERSION} - 설명\n\n/$label - 설명문을 띄워요.\n/$label on - 리포그램을 적용시켜요.\n/$label off - 리포그램을 해제시켜요.\n\n이 설명문은 우리글의 첫째 모음 없이 쓰였어요.")
        } else {
            when (arguments[0]) {
                "on" -> {
                    runner.useLipogram = true
                    group.sendMessage("${runner.name} 님에게 리포그램을 적용시켰어요!")
                }
                "off" -> {
                    runner.useLipogram = false
                    group.sendMessage("${runner.name} 님에게 리포그램을 해제시켰어요!")
                }
                else -> {
                    group.sendMessage("이게 뭐죠? /$label 로 설명문을 보고 시도해주세요!")
                }
            }
        }
    }
}