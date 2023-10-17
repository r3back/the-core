package com.qualityplus.skills.base.skill.skills;

import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import com.qualityplus.skills.base.reward.StatReward;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Utility class for Dungeoneering Skill
 */
@Getter
@Setter
@NoArgsConstructor
public final class DungeoneeringSkill extends Skill {
    private Map<String, Double> rewardsByName;
    private Map<String, Double> mythicMobRewards;

    /**
     * Makes a Dungeoneering Skill
     *
     * @param id                  Id
     * @param enabled             Enabled
     * @param displayName         Display Name
     * @param description         Description
     * @param skillGUIOptions     {@link GUIOptions}
     * @param initialAmount       Initial Amount
     * @param maxLevel            Max Level
     * @param xpRequirements      Xp Requirements
     * @param skillInfoInGUI      Skill Info In GUI
     * @param statRewards         Stat Rewards
     * @param skillInfoInMessage  Skill Info In Message
     * @param commandRewards      Command Rewards
     * @param rewardsByName       Rewards By Name
     * @param mythicMobRewards    Mythic Mob Rewards
     */
    @Builder
    public DungeoneeringSkill(final String id,
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
                              final Map<String, Double> rewardsByName,
                              final Map<String, Double> mythicMobRewards) {
        super(id, enabled, displayName,
                description, skillGUIOptions,
                initialAmount, maxLevel,
                xpRequirements, skillInfoInGUI,
                statRewards, skillInfoInMessage,
                commandRewards);
        this.rewardsByName = rewardsByName;
        this.mythicMobRewards = mythicMobRewards;
    }
}
