package com.qualityplus.auction.base.commands;

import com.qualityplus.auction.api.box.Box;
import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.auction.base.gui.main.MainAuctionGUI;
import com.qualityplus.auction.base.searcher.AuctionSearcher;
import com.qualityplus.auction.base.searcher.filters.BinFilter;
import com.qualityplus.auction.base.searcher.filters.SortFilter;
import com.qualityplus.auction.base.searcher.filters.StringFilter;
import com.qualityplus.auction.util.AuctionFilterUtil;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

@Component
public final class OpenCommand extends AssistantCommand {
    private @Inject Box box;

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if(args.length == 1) {
            player.openInventory(new MainAuctionGUI(box, AuctionFilterUtil.getSearcher(box), player.getUniqueId()).getInventory());
        }else
            player.sendMessage(StringUtils.color(box.files().messages().pluginMessages.useSyntax.replace("%usage%", syntax)));

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return args.length == 2 ? null : Collections.emptyList();
    }

    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    public void register(@Inject Box box){
        TheAssistantPlugin.getAPI().getCommandProvider().registerCommand(this, e -> e.getCommand().setDetails(box.files().commands().openCommand));
    }
}