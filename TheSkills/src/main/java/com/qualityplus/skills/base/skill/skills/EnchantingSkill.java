package com.qualityplus.skills.base.skill.skills;

import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import com.qualityplus.skills.TheSkills;
import com.qualityplus.skills.base.reward.StatReward;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.enchantment.EnchantItemEvent;

import java.util.List;
import java.util.Map;

/**
 * Utility class for enchanting skill
 */
@Getter
@Setter
@NoArgsConstructor
public final class EnchantingSkill extends Skill {
    private Map<Integer, Double> rewardsPerLevel;
    private double rewardForAllEnchantments;

    /**
     * Makes an enchanting skills
     *
     * @param id                        Id
     * @param enabled                   Enabled
     * @param displayName               Display Name
     * @param description               Description
     * @param skillGUIOptions           {@link GUIOptions}
     * @param initialAmount             Initial Amount
     * @param maxLevel                  Max Level
     * @param xpRequirements            Xp Requirements
     * @param skillInfoInGUI            Skill Info In GUI
     * @param statRewards               Stat Rewards
     * @param skillInfoInMessage        Skill Info In Message
     * @param commandRewards            Command Rewards
     * @param rewardsPerLevel           Rewards Per Level
     * @param rewardForAllEnchantments  Reward For All Enchantments
     */
    @Builder
    public EnchantingSkill(final String id,
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
                           final Map<Integer, Double> rewardsPerLevel,
                           final double rewardForAllEnchantments) {
        super(id, enabled, displayName,
                description, skillGUIOptions,
                initialAmount, maxLevel,
                xpRequirements, skillInfoInGUI,
                statRewards, skillInfoInMessage,
                commandRewards);
        this.rewardsPerLevel = rewardsPerLevel;
        this.rewardForAllEnchantments = rewardForAllEnchantments;
    }

    /**
     * Adds enchant item event
     *
     * @param e {@link EnchantItemEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEnchantItemEvent(final EnchantItemEvent e) {
        final Player player = e.getEnchanter();

        final double xp = this.rewardForAllEnchantments + getExtraXp(e);

        if (xp <= 0) {
            return;
        }

        TheSkills.getApi().getSkillsService().addXp(player, true, true, this, xp);
    }

    private double getExtraXp(final EnchantItemEvent event) {
        double xp = 0;
        for (Integer i : event.getEnchantsToAdd().values()) {
            xp += this.rewardsPerLevel.getOrDefault(i, 0d);
        }
        return xp;

    }
}
