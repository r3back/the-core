package com.qualityplus.auction.base.commands;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.base.config.Messages;
import com.qualityplus.assistant.lib.eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;

import com.qualityplus.assistant.lib.eu.okaeri.platform.bukkit.annotation.Delayed;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

/**
 * Utility class for help commands
 */
@Component
public final class HelpCommand extends AssistantCommand {
    private @Inject Box box;

    @Override
    public boolean execute(final CommandSender sender, final String[] args) {
        final Messages.PluginMessages msg = this.box.files().messages().getPluginMessages();
        sendHelpCommands(sender, args, TheAssistantPlugin.getAPI().getCommandProvider(), msg.getHelpHeader(),
                msg.getHelpMessage(), msg.getHelpfooter(), msg.getNextPage(),
                msg.getPreviousPage(), msg.getHelpPageHoverMessage());
        return true;
    }

    @Override
    public List<String> onTabComplete(final CommandSender commandSender, final org.bukkit.command.Command command, final String label, final String[] args) {
        return Collections.emptyList();
    }

    /**
     * Adds a register
     * @param box {@link Box}
     */
    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    public void register(@Inject final Box box) {
        TheAssistantPlugin.getAPI().getCommandProvider().registerCommand(this, e -> e.getCommand().setDetails(box.files().commands().getHelpCommand()));
    }
}
