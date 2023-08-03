package com.qualityplus.auction.base.commands;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.auction.api.box.Box;
import com.qualityplus.assistant.lib.eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;;

import com.qualityplus.assistant.lib.eu.okaeri.platform.bukkit.annotation.Delayed;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

/**
 * Utility class for reload command
 */
@Component
public final class ReloadCommand extends AssistantCommand {
    private @Inject Box box;

    @Override
    public boolean execute(final CommandSender sender, final String[] args) {
        final Player player = (Player) sender;

        if (args.length == 1) {
            this.box.files().reloadFiles();

            player.sendMessage(StringUtils.color(this.box.files().messages().getPluginMessages().getSuccessfullyReloaded()));
        } else {
            player.sendMessage(StringUtils.color(this.box.files().messages().getPluginMessages().getUseSyntax().replace("%usage%", syntax)));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(final CommandSender commandSender, final org.bukkit.command.Command command, final String label, final String[] args) {
        return Collections.emptyList();
    }

    /**
     * Adds a register
     *
     * @param box {@link Box}
     */
    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    public void register(@Inject final Box box) {
        TheAssistantPlugin.getAPI().getCommandProvider().registerCommand(this, e -> e.getCommand().setDetails(box.files().commands().getReloadCommand()));
    }
}
