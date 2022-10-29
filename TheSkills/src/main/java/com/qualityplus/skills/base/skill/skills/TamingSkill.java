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
public final class TamingSkill extends Skill {
    @Builder
    public TamingSkill(String id, boolean enabled, String displayName, List<String> description, StatRewards statRewards, CommandRewards commandRewards,
                       GUIOptions skillGUIOptions, Map<Integer, List<String>> skillsInfoInGUI, Map<Integer, List<String>> skillsInfoInMessage,
                       Map<Integer, Double> xpRequirements, int maxLevel) {
        super(id, enabled, displayName, description, maxLevel, statRewards, commandRewards, skillGUIOptions, xpRequirements, skillsInfoInGUI, skillsInfoInMessage);
    }
}
