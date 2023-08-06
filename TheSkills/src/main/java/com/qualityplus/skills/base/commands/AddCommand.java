package com.qualityplus.skills.base.commands;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.list.ListBuilder;
import com.qualityplus.assistant.util.number.NumberUtil;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.skills.api.box.Box;
import com.qualityplus.skills.api.effect.CommonObject;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.registry.Skills;
import com.qualityplus.skills.base.stat.Stat;
import com.qualityplus.skills.base.stat.registry.Stats;
import com.qualityplus.skills.persistance.data.UserData;
import com.qualityplus.assistant.lib.eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;;

import com.qualityplus.assistant.lib.eu.okaeri.platform.bukkit.annotation.Delayed;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Utility class for Add command
 */
@Component
public final class AddCommand extends AssistantCommand {
    private @Inject Box box;

    @Override
    public boolean execute(final CommandSender sender, final String[] args) {
        if (args.length == 4) {

            final Player player = Bukkit.getPlayer(args[1]);

            if (player == null) {
                sender.sendMessage(StringUtils.color(this.box.files().messages().getPluginMessages().getInvalidPlayer()));
                return false;
            }

            final Skill skill = Skills.getByID(args[2]);
            final Stat stat = Stats.getByID(args[2]);

            final CommonObject object = skill != null ? skill : stat;

            if (object == null) {
                sender.sendMessage(StringUtils.color(this.box.files().messages().getSkillsMessages().getInvalidObject()));
                return false;
            }

            final Integer level = NumberUtil.intOrNull(args[3]);

            if (level == null) {
                sender.sendMessage(StringUtils.color(this.box.files().messages().getPluginMessages().getInvalidAmount()));
                return false;
            }

            final Optional<UserData> data = this.box.service().getData(player.getUniqueId());

            data.ifPresent(userData -> userData.getSkills().addLevel(object.getId(), level));

            final List<IPlaceholder> placeholders = Arrays.asList(new Placeholder("amount", level),
                    new Placeholder("object", object.getId()),
                    new Placeholder("player", player.getName()),
                    new Placeholder("new_level", data.map(d -> d.getSkills().getLevel(object.getId())).orElse(0)));

            sender.sendMessage(StringUtils.processMulti(this.box.files().messages().getSkillsMessages().getAddedAmount(), placeholders));

        } else {
            sender.sendMessage(StringUtils.color(this.box.files().messages().getPluginMessages().getUseSyntax()));
        }
        return true;

    }

    @Override
    public List<String> onTabComplete(final CommandSender commandSender,
                                      final org.bukkit.command.Command command,
                                      final String label,
                                      final String[] args) {
        return args.length == 2 ? null : args.length == 3 ?
                new ListBuilder<String>()
                        .with(Stats.values().stream().map(CommonObject::getId).collect(Collectors.toList()))
                        .with(Skills.values().stream().map(CommonObject::getId).collect(Collectors.toList()))
                        .get()
                : args.length == 4 ? NumberUtil.stringSecuence(0, 10) : Collections.emptyList();
    }

    /**
     * Adds a register
     *
     * @param box {@link Box}
     */
    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    public void register(@Inject final Box box) {
        TheAssistantPlugin.getAPI().getCommandProvider().registerCommand(this, e -> e.getCommand().setDetails(box.files().commands().getAddCommand()));
    }
}
