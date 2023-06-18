package com.qualityplus.anvil.base.commands

import com.qualityplus.anvil.api.box.Box
import com.qualityplus.anvil.base.gui.anvilmain.AnvilMainGUI
import com.qualityplus.anvil.base.session.AnvilSessionImpl
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

@Component
class OpenAnvilCommand : AssistantCommand() {
    @Inject
    private lateinit var box: Box

    override fun execute(sender: CommandSender, args: Array<String>): Boolean {
        val syntaxMsg =
            StringUtils.color(box.files.messages().pluginMessages.useSyntax.replace("%usage%", syntax))
        val mustBeAPlayer = StringUtils.color(
            box.files.messages().pluginMessages.mustBeAPlayer.replace("%usage%", syntax)
        )
        val invalidPlayer = StringUtils.color(
            box.files.messages().pluginMessages.invalidPlayer.replace("%usage%", syntax)
        )
        return openInventory(
            args,
            sender,
            AnvilMainGUI(box, AnvilSessionImpl(null, null, null)),
            syntaxMsg,
            mustBeAPlayer,
            invalidPlayer
        )
    }

    override fun onTabComplete(
        commandSender: CommandSender,
        command: Command,
        label: String,
        args: Array<String>
    ): List<String> {
        return if (args.size == 2) null else emptyList()
    }

    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    fun register(@Inject box: Box) {
        TheAssistantPlugin.getAPI().commandProvider.registerCommand(this) { e: CommandSetupEvent<AssistantCommand> ->
            e.command.setDetails(
                box.getFiles()!!.commands()!!.openCommand
            )
        }
    }
}