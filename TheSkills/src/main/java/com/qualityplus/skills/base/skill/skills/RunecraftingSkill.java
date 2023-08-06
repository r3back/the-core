package com.qualityplus.skills.base.skill.skills;

import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import com.qualityplus.skills.base.reward.StatReward;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Utility class for runecrafting skills
 */
@Data
@NoArgsConstructor
public final class RunecraftingSkill extends Skill {
    private double reward;

     /**
     * Makes a block break skills
     *
     * @param id                 Id
     * @param enabled            Enabled
     * @param displayName        Display Name
     * @param description        Description
     * @param skillGUIOptions    {@link GUIOptions}
     * @param initialAmount      Initial Amount
     * @param maxLevel           Max Level
     * @param xpRequirements     Xp Requirements
     * @param skillInfoInGUI     Skills indo In GUI
     * @param statRewards        Stats Rewards
     * @param skillInfoInMessage Skills Info In Message
     * @param commandRewards     Command Rewards
     * @param reward            Reward
     */
    @Builder
    public RunecraftingSkill(final String id,
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
                             final double reward) {
        super(id, enabled, displayName,
                description, skillGUIOptions,
                initialAmount, maxLevel,
                xpRequirements, skillInfoInGUI,
                statRewards, skillInfoInMessage,
                commandRewards);
        this.reward = reward;
    }
}
