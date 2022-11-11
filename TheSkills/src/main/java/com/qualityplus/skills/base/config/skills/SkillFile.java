package com.qualityplus.skills.base.config.skills;

import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import com.qualityplus.skills.base.config.common.BaseFile;
import com.qualityplus.skills.base.reward.StatReward;
import com.qualityplus.skills.base.skill.gui.GUIOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SkillFile extends BaseFile {

    public Map<Integer, Double> getXpRequirements();

    public void setXpRequirements(Map<Integer, Double> xpRequirements);

    public Map<Integer, List<String>> getSkillInfoInGUI();

    public void setSkillInfoInGUI(Map<Integer, List<String>> skillInfoInGUI);

    public Map<Integer, List<StatReward>> getStatRewards();

    public void setStatRewards(Map<Integer, List<StatReward>> statRewards);

    public Map<Integer, List<String>> getSkillInfoInMessage();

    public void setSkillInfoInMessage(Map<Integer, List<String>> skillInfoInMessage);

    public Map<Integer, List<CommandReward>> getCommandRewards();

    public void setCommandRewards(Map<Integer, List<CommandReward>> commandRewards);
}
