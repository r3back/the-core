package com.qualityplus.anvil.base.commands

import com.qualityplus.anvil.api.box.Box
import com.qualityplus.assistant.TheAssistantPlugin
import com.qualityplus.assistant.api.commands.command.AssistantCommand
import com.qualityplus.assistant.api.commands.setup.event.CommandSetupEvent
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent
import eu.okaeri.injector.annotation.Inject
import eu.okaeri.platform.bukkit.annotation.Delayed
import eu.okaeri.platform.core.annotation.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

@Component
class HelpCommand : AssistantCommand() {
    @Inject
    private val box: Box? = null
    override fun execute(sender: CommandSender, args: Array<String>): Boolean {
        val msg = box!!.getFiles()!!.messages()!!.pluginMessages
        sendHelpCommands(
            sender,
            args,
            TheAssistantPlugin.getAPI().commandProvider,
            msg!!.helpHeader,
            msg.helpMessage,
            msg.helpfooter,
            msg.nextPage,
            msg.previousPage,
            msg.helpPageHoverMessage
        )
        return true
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
                box.getFiles()!!.commands()!!.helpCommand
            )
        }
    }
}