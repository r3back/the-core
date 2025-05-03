package com.qualityplus.skills.base.commands.items;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.lib.eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.lib.eu.okaeri.platform.bukkit.annotation.Delayed;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.skills.VoxSkills;
import com.qualityplus.skills.api.box.Box;
import com.qualityplus.skills.api.effect.CommonObject;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.stat.Stat;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public final class ViewItemStatsCommand extends AssistantCommand {
    private @Inject Box box;

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length == 1) {
            final Player player = (Player) sender;

            final ItemStack itemStack = player.getInventory().getItemInMainHand();
            if (BukkitItemUtil.isNull(itemStack)) {
                player.sendMessage(StringUtils.color("&cYou must have an item in your hand!"));
                return false;
            }

            final Map<CommonObject, Double> itemStats = new HashMap<>(VoxSkills.getApi().getItemAttributes(itemStack.clone()));

            player.sendMessage(StringUtils.color("&6&lItem Stats:"));
            itemStats.forEach((st, lvl) -> {
                if (st instanceof Perk) {
                    return;
                }

                player.sendMessage(StringUtils.color("&6&l" + st.getId() + ": &e" + lvl));
            });

            player.sendMessage(" ");
            player.sendMessage(StringUtils.color("&6&lItem Perks:"));
            itemStats.forEach((st, lvl) -> {
                if (st instanceof Stat) {
                    return;
                }

                player.sendMessage(StringUtils.color("&6&l" + st.getId() + ": &e" + lvl));
            });
        } else
            sender.sendMessage(StringUtils.color(box.files().messages().pluginMessages.useSyntax));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return Collections.emptyList();
    }

    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    public void register(@Inject Box box) {
        TheAssistantPlugin.getAPI().getCommandProvider().registerCommand(this, e -> e.getCommand().setDetails(box.files().commands().viewItemStatsCommand));
    }
}
