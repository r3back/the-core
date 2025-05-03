package com.qualityplus.pets.api.skills;

import org.bukkit.entity.Player;

public interface SkillDependency {
    void addStat(Player player, String stat, double amount);

    void removeStat(Player player, String stat, double amount);
}
