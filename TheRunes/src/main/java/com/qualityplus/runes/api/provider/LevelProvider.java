package com.qualityplus.runes.api.provider;

import org.bukkit.entity.Player;

public interface LevelProvider {
    int getLevel(Player player);

    void addXp(Player player, double xp);
}
