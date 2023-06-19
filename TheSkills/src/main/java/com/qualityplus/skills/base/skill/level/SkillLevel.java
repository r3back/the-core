package com.qualityplus.skills.base.skill.level;

import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import com.qualityplus.skills.base.reward.StatReward;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class SkillLevel extends OkaeriConfig {
    private double requiredXp;
    private List<String> skillInfoInGUI;
    private List<StatReward> statRewards;
    private List<String> skillInfoInMessage;
    private List<CommandReward> commandRewards;
}
