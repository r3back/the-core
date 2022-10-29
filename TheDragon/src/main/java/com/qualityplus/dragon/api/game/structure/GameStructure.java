package com.qualityplus.dragon.api.game.structure;

import org.bukkit.Location;
import org.bukkit.World;

public interface GameStructure {
    void removeStructure();

    Location getLocation();

    World getWorld();
}
