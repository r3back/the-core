package com.qualityplus.dragon.api.game.dragon;

import org.bukkit.Location;
import org.bukkit.entity.EnderDragon;

/**
 * TheDragonEntity Implementation
 */
public interface TheDragonEntity {
    /**
     * Spawn TheDragonEntity
     *
     * @param location Location to be spawned
     * @return Spawned EnderDragon Entity
     */
    EnderDragon spawn(Location location);

    /**
     *
     * @return TheDragonEntity's Displayname
     */
    String getDisplayName();

    /**
     *
     * @return TheDragonEntity's Max Health
     */
    double getMaxHealth();

    /**
     *
     * @return TheDragonEntity's Health
     */
    double getHealth();

    /**
     *
     * @return TheDragonEntity chance to be choose during a game
     */
    double getChance();

    /**
     *
     * @return TheDragonEntity's Id
     */
    String getId();

    /**
     *
     * @return XP Amount that gives when it's killed
     */
    double getXp();
}
