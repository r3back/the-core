package com.qualityplus.skills.api.service;

import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.persistance.data.UserData;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public interface SkillsService {
    Optional<UserData> getSkillsData(UUID uuid);

    void addData(UserData data);

    void removeData(UserData data);

    void addXp(Player player, boolean useMulti, boolean showXpActionBar, Skill skill, double xp);
}
