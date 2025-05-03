package com.qualityplus.runes.base.provider;

import com.qualityplus.runes.api.provider.LevelProvider;
import com.qualityplus.skills.VoxSkills;
import org.bukkit.entity.Player;

public final class RuneCraftLevelProvider implements LevelProvider {
    @Override
    public double getLevel(Player player) {
        return VoxSkills.getApi().getSkillsService()
                .getData(player.getUniqueId())
                .map(userData -> userData.getSkills().getLevel("runecrafting"))
                .orElse(0D);
    }

    @Override
    public void addXp(Player player, double xp) {
        VoxSkills.getApi().getSkillsService()
                .getData(player.getUniqueId())
                .ifPresent(userData -> userData.getSkills().addXp("runecrafting", xp));
    }
}
