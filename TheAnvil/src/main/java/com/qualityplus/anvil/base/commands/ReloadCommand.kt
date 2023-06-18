package com.qualityplus.anvil.base.commands

import com.qualityplus.anvil.api.box.Box
import com.qualityplus.assistant.TheAssistantPlugin
import com.qualityplus.assistant.api.commands.command.AssistantCommand
import com.qualityplus.assistant.api.commands.setup.event.CommandSetupEvent
import com.qualityplus.assistant.util.StringUtils
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent
import eu.okaeri.injector.annotation.Inject
import eu.okaeri.platform.bukkit.annotation.Delayed
import eu.okaeri.platform.core.annotation.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Component
class ReloadCommand : AssistantCommand() {
    @Inject
    private val box: Box? = null
    override fun execute(sender: CommandSender, args: Array<String>): Boolean {
        val player = sender as Player
        if (args.size == 1) {
            box!!.getFiles()!!.reloadFiles()
            player.sendMessage(StringUtils.color(box.getFiles()!!.messages()!!.pluginMessages.successfullyReloaded))
        } else {
            player.sendMessage(
                StringUtils.color(
                    box!!.getFiles()!!.messages()!!.pluginMessages.useSyntax.replace("%usage%", syntax)
                )
            )
        }
        return false
    }

    override fun onTabComplete(
        commandSender: CommandSender,
        command: Command,
        label: String,
        args: Array<String>
    ): List<String> {
        return emptyList()
    }

    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    fun register(@Inject box: Box) {
        TheAssistantPlugin.getAPI().commandProvider.registerCommand(this) { e: CommandSetupEvent<AssistantCommand> ->
            e.command.setDetails(
                box.getFiles()!!.commands()!!.reloadCommand
            )
        }
    }
}