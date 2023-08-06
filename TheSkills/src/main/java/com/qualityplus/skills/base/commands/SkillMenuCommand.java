package com.qualityplus.skills.base.commands;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.skills.api.box.Box;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.registry.Skills;
import com.qualityplus.skills.gui.sub.SkillGUI;
import com.qualityplus.assistant.lib.eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;;

import com.qualityplus.assistant.lib.eu.okaeri.platform.bukkit.annotation.Delayed;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

/**
 * Utility class for skill menu command
 */
@Component
public final class SkillMenuCommand extends AssistantCommand {
    private @Inject Box box;

    @Override
    public boolean execute(final CommandSender sender, final String[] args) {
        if (args.length == 2) {
            final Player player = (Player) sender;

            final Skill skill = Skills.getByID(args[1]);

            if (skill == null) {
                player.sendMessage(StringUtils.color(this.box.files().messages().getSkillsMessages().getInvalidObject()));
                return false;
            }

            if (!skill.isEnabled()) {
                player.sendMessage(StringUtils.color(this.box.files().messages().getSkillsMessages().getSkillsIsDisabled()));
                return false;
            }

            player.openInventory(new SkillGUI(this.box, player, skill, 1).getInventory());
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
        return Collections.emptyList();
    }

    /**
     * Adds a register
     *
     * @param box {@link Box}
     */
    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    public void register(@Inject final Box box) {
        TheAssistantPlugin.getAPI().getCommandProvider().registerCommand(this, e -> e.getCommand().setDetails(box.files().commands().getSkillMenuCommand()));
    }
}
