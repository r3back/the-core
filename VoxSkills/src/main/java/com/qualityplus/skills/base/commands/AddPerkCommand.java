package com.qualityplus.skills.base.commands;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.api.util.NumberUtil;
import com.qualityplus.assistant.lib.eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.lib.eu.okaeri.platform.bukkit.annotation.Delayed;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.list.ListBuilder;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.skills.api.box.Box;
import com.qualityplus.skills.api.effect.CommonObject;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.perk.registry.Perks;
import com.qualityplus.skills.persistance.data.UserData;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public final class AddPerkCommand extends AssistantCommand {
    private @Inject Box box;

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length == 4) {

            Player player = Bukkit.getPlayer(args[1]);

            if (player == null) {
                sender.sendMessage(StringUtils.color(box.files().messages().pluginMessages.invalidPlayer));
                return false;
            }

            final Perk object = Perks.getByID(args[2]);

            if (object == null) {
                sender.sendMessage(StringUtils.color(box.files().messages().skillsMessages.invalidObject));
                return false;
            }

            Double level = NumberUtil.doubleOrNull(args[3]);

            if (level == null) {
                sender.sendMessage(StringUtils.color(box.files().messages().pluginMessages.invalidAmount));
                return false;
            }

            Optional<UserData> data = box.service().getData(player.getUniqueId());

            data.ifPresent(userData -> userData.getSkills().addLevel(object.getId(), level));

            List<IPlaceholder> placeholders = Arrays.asList(new Placeholder("amount", level)
                    , new Placeholder("object", object.getId())
                    , new Placeholder("player", player.getName())
                    , new Placeholder("new_level", data.map(d -> d.getSkills().getLevel(object.getId())).orElse(0D)));

            sender.sendMessage(StringUtils.processMulti(box.files().messages().skillsMessages.addedAmount, placeholders));

        } else
            sender.sendMessage(StringUtils.color(box.files().messages().pluginMessages.useSyntax));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return args.length == 2 ? null : args.length == 3 ?
                new ListBuilder<String>()
                        .with(Perks.values().stream().map(CommonObject::getId).collect(Collectors.toList()))
                        .get()
                : args.length == 4 ? NumberUtil.stringSecuence(0, 10) : Collections.emptyList();
    }

    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    public void register(@Inject Box box) {
        TheAssistantPlugin.getAPI().getCommandProvider().registerCommand(this, e -> e.getCommand().setDetails(box.files().commands().addPerkCommand));
    }
}
