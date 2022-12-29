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

@Getter
@Setter
@NoArgsConstructor
public final class DungeoneeringSkill extends Skill {
    private Map<String, Double> rewardsByName;
    private Map<String, Double> mythicMobRewards;

    @Builder
    public DungeoneeringSkill(String id, boolean enabled, String displayName, List<String> description, GUIOptions skillGUIOptions, double initialAmount, int maxLevel, Map<Integer, Double> xpRequirements, Map<Integer, List<String>> skillInfoInGUI, Map<Integer, List<StatReward>> statRewards, Map<Integer, List<String>> skillInfoInMessage, Map<Integer, List<CommandReward>> commandRewards, Map<String, Double> rewardsByName, Map<String, Double> mythicMobRewards) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, maxLevel, xpRequirements, skillInfoInGUI, statRewards, skillInfoInMessage, commandRewards);
        this.rewardsByName = rewardsByName;
        this.mythicMobRewards = mythicMobRewards;
    }
}
