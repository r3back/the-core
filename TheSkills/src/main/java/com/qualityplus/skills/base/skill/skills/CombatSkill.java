package com.qualityplus.skills.base.skill.skills;

import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import com.qualityplus.assistant.base.event.PlayerKillEvent;
import com.qualityplus.skills.TheSkills;
import com.qualityplus.skills.base.reward.StatReward;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.util.SkillsPlayerUtil;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import java.util.List;
import java.util.Map;

/**
 * Utility class for combat skills
 */
@Data
@NoArgsConstructor
public final class CombatSkill extends Skill {
    private Map<EntityType, Double> rewards;

    /**
     * Makes a combat skills
     *
     * @param id                        Id
     * @param enabled                  Enabled
     * @param displayName              Display Name
     * @param description              Description
     * @param skillGUIOptions          {@link GUIOptions}
     * @param initialAmount            Initial Amount
     * @param maxLevel                 Max Level
     * @param xpRequirements           Xp Requirements
     * @param skillInfoInGUI           Skill Info In GUI
     * @param statRewards              Stat Rewards
     * @param skillInfoInMessage       Skill Info In Message
     * @param commandRewards           Command Rewards
     * @param rewards                  Rewards
     */
    @Builder
    public CombatSkill(final String id,
                       final boolean enabled,
                       final String displayName,
                       final List<String> description,
                       final GUIOptions skillGUIOptions,
                       final double initialAmount,
                       final int maxLevel,
                       final Map<Integer, Double> xpRequirements,
                       final Map<Integer, List<String>> skillInfoInGUI,
                       final Map<Integer, List<StatReward>> statRewards,
                       final Map<Integer, List<String>> skillInfoInMessage,
                       final Map<Integer, List<CommandReward>> commandRewards,
                       final Map<EntityType, Double> rewards) {
        super(id, enabled, displayName,
                description, skillGUIOptions,
                initialAmount, maxLevel,
                xpRequirements, skillInfoInGUI,
                statRewards, skillInfoInMessage,
                commandRewards);
        this.rewards = rewards;
    }


    /**
     * Adds an on kill mob
     *
     * @param e {@link PlayerKillEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onKillMob(final PlayerKillEvent e) {
        final Player player = e.getPlayer();

        if (!SkillsPlayerUtil.isInSurvival(player)) {
            return;
        }

        final Entity entity = e.getKilled();

        final double xp = this.rewards.getOrDefault(entity.getType(), 0D);

        if (xp <= 0) {
            return;
        }

        TheSkills.getApi().getSkillsService().addXp(player, true, true, this, xp);
    }
}
