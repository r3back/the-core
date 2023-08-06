package com.qualityplus.skills.api.service;

import com.qualityplus.assistant.api.service.DataManagementService;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.persistance.data.UserData;
import org.bukkit.entity.Player;

/**
 * Utility class for skills service
 */
public interface SkillsService extends DataManagementService<UserData> {
    /**
     * Adds a xp
     *
     * @param player          {@link Player}
     * @param useMulti        Use Multi
     * @param showXpActionBar Show Xp Action Bar
     * @param skill           {@link Skill}
     * @param xp              Xp
     */
    public void addXp(Player player, boolean useMulti, boolean showXpActionBar, Skill skill, double xp);
}
