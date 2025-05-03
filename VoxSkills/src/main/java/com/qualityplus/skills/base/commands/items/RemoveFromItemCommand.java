package com.qualityplus.skills.base.commands.items;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.api.util.NumberUtil;
import com.qualityplus.assistant.lib.eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.lib.eu.okaeri.platform.bukkit.annotation.Delayed;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.list.ListBuilder;
import com.qualityplus.skills.VoxSkills;
import com.qualityplus.skills.api.box.Box;
import com.qualityplus.skills.api.effect.CommonObject;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.perk.registry.Perks;
import com.qualityplus.skills.base.stat.Stat;
import com.qualityplus.skills.base.stat.registry.Stats;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public final class RemoveFromItemCommand extends AssistantCommand {
    private @Inject Box box;

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length == 3) {
            final Player player = (Player) sender;

            final ItemStack itemStack = player.getInventory().getItemInMainHand();
            if (BukkitItemUtil.isNull(itemStack)) {
                player.sendMessage(StringUtils.color("&cYou must have an item in your hand!"));
                return false;
            }

            CommonObject tempObj = Perks.getByID(args[1]);

            if (tempObj == null) {
                tempObj = Stats.getByID(args[1]);
            }

            final CommonObject object = tempObj;

            if (object == null) {
                player.sendMessage(StringUtils.color(box.files().messages().skillsMessages.invalidObject));
                return false;
            }

            final Double level = NumberUtil.doubleOrNull(args[2]);

            if (level == null) {
                player.sendMessage(StringUtils.color(box.files().messages().pluginMessages.invalidAmount));
                return false;
            }

            final Map<CommonObject, Double> itemAttributes = new HashMap<>(VoxSkills.getApi().getItemAttributes(itemStack.clone()));
            final Double oldStat = itemAttributes.getOrDefault(object, 0D);
            itemAttributes.put(object, Math.max(0, oldStat - level));

            final ItemStack newItem = VoxSkills.getApi().setItemAttributes(itemStack.clone(), itemAttributes);
            Bukkit.getScheduler().runTaskLater(VoxSkills.getApi().getPlugin(), () -> player.getInventory().setItemInMainHand(newItem), 10);

            player.sendMessage(StringUtils.color("&6&lNew Item Perks:"));
            itemAttributes.forEach((st, lvl) -> {
                if (!(st instanceof Perk)) {
                    return;
                }

                if (!Objects.equals(st.getId(), object.getId())) {
                    player.sendMessage(StringUtils.color("&6&l" + st.getId() + ": &e" + lvl));
                } else {
                    player.sendMessage(StringUtils.color("&6&l" + st.getId() + ": &7" + oldStat + " &a➔ &e" + lvl));
                }
            });
            player.sendMessage(StringUtils.color(" "));

            player.sendMessage(StringUtils.color("&6&lNew Item Stats:"));
            itemAttributes.forEach((st, lvl) -> {
                if (!(st instanceof Stat)) {
                    return;
                }

                if (!Objects.equals(st.getId(), object.getId())) {
                    player.sendMessage(StringUtils.color("&6&l" + st.getId() + ": &e" + lvl));
                } else {
                    player.sendMessage(StringUtils.color("&6&l" + st.getId() + ": &7" + oldStat + " &c➔ &e" + lvl));
                }
            });
        } else
            sender.sendMessage(StringUtils.color(box.files().messages().pluginMessages.useSyntax));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return args.length == 2 ?
                new ListBuilder<String>()
                        .with(Stats.values().stream().map(CommonObject::getId).collect(Collectors.toList()))
                        .with(Perks.values().stream().map(CommonObject::getId).collect(Collectors.toList()))
                        .get()
                : args.length == 3 ? NumberUtil.stringSecuence(0, 10) : Collections.emptyList();
    }

    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    public void register(@Inject Box box) {
        TheAssistantPlugin.getAPI().getCommandProvider().registerCommand(this, e -> e.getCommand().setDetails(box.files().commands().removeFromItemCommand));
    }
}
