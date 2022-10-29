package com.qualityplus.dragon.api.game.guardian;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

/**
 * Guardian Implementation
 */
public interface Guardian {
    /**
     * Spawn a Guardian in specific Location
     *
     * @param location Location to be spawned
     * @return Entity once spawned
     */
    Entity spawn(Location location);

    /**
     *
     * @return Guardian ID
     */
    String getID();
}
