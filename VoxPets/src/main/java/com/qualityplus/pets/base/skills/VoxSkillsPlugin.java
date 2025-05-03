package com.qualityplus.pets.base.skills;

import com.qualityplus.pets.api.skills.SkillDependency;
import com.qualityplus.skills.VoxSkills;
import org.bukkit.entity.Player;

public final class VoxSkillsPlugin implements SkillDependency {
    @Override
    public void addStat(Player player, String stat, double amount) {
        VoxSkills.getApi()
                .getSkillsService()
                .getData(player.getUniqueId())
                .ifPresent(userData -> userData.getSkills().addArmor(stat, amount));
    }

    @Override
    public void removeStat(Player player, String stat, double amount) {
        VoxSkills.getApi()
                .getSkillsService()
                .getData(player.getUniqueId())
                .ifPresent(userData -> userData.getSkills().removeArmor(stat, amount));
    }
}
