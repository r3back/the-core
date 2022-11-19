package com.qualityplus.minions.base.commands;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.minions.api.box.Box;
import com.qualityplus.minions.api.edition.SoulEdition;
import com.qualityplus.minions.base.gui.allsouls.AllSoulsGUI;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

@Component
public final class SoulsCommand extends AssistantCommand {
    private @Inject SoulEdition edition;
    private @Inject Box box;

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(args.length == 1)
            player.openInventory(new AllSoulsGUI(box, 1, edition).getInventory());
        else
            player.sendMessage(StringUtils.color(box.files().messages().pluginMessages.useSyntax.replace("%usage%", syntax)));

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return args.length == 2 ? null : Collections.emptyList();
    }

    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    public void register(@Inject Box box){
        TheAssistantPlugin.getAPI().getCommandProvider().registerCommand(this, e -> e.getCommand().setDetails(box.files().commands().editCommand));
    }
}