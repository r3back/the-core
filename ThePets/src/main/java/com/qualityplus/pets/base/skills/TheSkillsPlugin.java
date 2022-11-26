package com.qualityplus.pets.base.skills;

import com.qualityplus.pets.api.skills.SkillDependency;
import com.qualityplus.skills.TheSkills;
import org.bukkit.entity.Player;

public final class TheSkillsPlugin implements SkillDependency {
    @Override
    public void addStat(Player player, String stat, double amount) {
        TheSkills.getApi()
                .getSkillsService()
                .getData(player.getUniqueId())
                .ifPresent(userData -> userData.getSkills().addArmor(stat, (int)amount));
    }

    @Override
    public void removeStat(Player player, String stat, double amount) {
        TheSkills.getApi()
                .getSkillsService()
                .getData(player.getUniqueId())
                .ifPresent(userData -> userData.getSkills().removeArmor(stat, (int)amount));
    }
}
