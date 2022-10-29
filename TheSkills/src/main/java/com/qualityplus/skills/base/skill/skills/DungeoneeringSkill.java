package com.qualityplus.skills.base.skill.skills;

import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.assistant.api.common.rewards.commands.CommandRewards;
import com.qualityplus.skills.base.reward.StatRewards;
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
    public DungeoneeringSkill(String id, boolean enabled, String displayName, List<String> description, StatRewards statRewards, CommandRewards commandRewards,
                              GUIOptions skillGUIOptions, Map<Integer, List<String>> skillsInfoInGUI, Map<Integer, List<String>> skillsInfoInMessage,
                              Map<Integer, Double> xpRequirements, int maxLevel, Map<String, Double> rewardsByName, Map<String, Double> mythicMobRewards) {
        super(id, enabled, displayName, description, maxLevel, statRewards, commandRewards, skillGUIOptions, xpRequirements, skillsInfoInGUI, skillsInfoInMessage);

        this.mythicMobRewards = mythicMobRewards;
        this.rewardsByName = rewardsByName;
    }
}
