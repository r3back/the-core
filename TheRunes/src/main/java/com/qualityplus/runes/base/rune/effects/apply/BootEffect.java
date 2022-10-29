package com.qualityplus.runes.base.rune.effects.apply;

import com.qualityplus.runes.base.rune.Rune;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public final class BootEffect implements ChainEffect{
    @Override
    public void execute(Player player, Entity entity, Rune rune) {
        Location location =  entity.getLocation().getBlock().getLocation().clone().add(new Vector(0, 0.2, 0));

        displayEffect(location, rune);

        displayFakeItems(location, rune);
    }
}
