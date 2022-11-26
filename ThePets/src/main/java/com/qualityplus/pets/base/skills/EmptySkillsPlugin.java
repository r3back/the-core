package com.qualityplus.pets.base.skills;

import com.qualityplus.pets.api.skills.SkillDependency;
import org.bukkit.entity.Player;

public final class EmptySkillsPlugin implements SkillDependency {
    @Override
    public void addStat(Player player, String stat, double amount) {

    }

    @Override
    public void removeStat(Player player, String stat, double amount) {

    }
}
