package com.qualityplus.skills.api.service;

import com.qualityplus.assistant.api.service.DataManagementService;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.persistance.data.UserData;
import org.bukkit.entity.Player;

public interface SkillsService extends DataManagementService<UserData> {
    void addXp(Player player, boolean useMulti, boolean showXpActionBar, Skill skill, double xp);
}
