package com.qualityplus.auction.base.commands;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.base.gui.main.MainAuctionGUI;
import com.qualityplus.auction.util.AuctionFilterUtil;
import com.qualityplus.assistant.lib.eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;

import com.qualityplus.assistant.lib.eu.okaeri.platform.bukkit.annotation.Delayed;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

/**
 * Utility class for open auction command
 */
@Component
public final class OpenAuctionCommand extends AssistantCommand {
    private @Inject Box box;

    @Override
    public boolean execute(final CommandSender sender, final String[] args) {
        final String syntaxMsg = StringUtils.color(this.box.files().messages().getPluginMessages().getUseSyntax().replace("%usage%", syntax));
        final String mustBeAPlayer = StringUtils.color(this.box.files().messages().getPluginMessages().getMustBeAPlayer().replace("%usage%", syntax));
        final String invalidPlayer = StringUtils.color(this.box.files().messages().getPluginMessages().getInvalidPlayer().replace("%usage%", syntax));

        final Player player = (Player) sender;

        return openInventory(args, sender, new MainAuctionGUI(this.box, AuctionFilterUtil.getSearcher(this.box), player.
                getUniqueId()), syntaxMsg, mustBeAPlayer, invalidPlayer);
    }

    @Override
    public List<String> onTabComplete(final CommandSender commandSender, final org.bukkit.command.Command command, final String label, final String[]  args) {
        return args.length == 2 ? null : Collections.emptyList();
    }

    /**
     * Adds a Register
     *
     * @param box {@link Box}
     */
    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    public void register(@Inject final Box box) {
        TheAssistantPlugin.getAPI().getCommandProvider().registerCommand(this, e -> e.getCommand().setDetails(box.files().commands().getOpenCommand()));
    }
}
