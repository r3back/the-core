package com.qualityplus.runes.api.provider;

import com.qualityplus.runes.base.rune.Rune;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public interface EffectProvider{
    void execute(Player player, Entity entity, Rune rune);
}
