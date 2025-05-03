package com.qualityplus.runes.base.rune;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

@FunctionalInterface
public interface EffectBridge {
    void applyExact(Player player, Entity entity, Rune rune);
}
