package com.qualityplus.dragon.api.controller;


import org.bukkit.Location;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;

/**
 * Implementation to manage Dragon during a game
 */
public interface DragonController {
    /**
     *
     * @return If Dragon is afk
     */
    boolean isAfk();

    /**
     * Force Dragon to move
     */
    void move();

    /**
     * Make a dragon move to last target
     */
    void target();

    /**
     * Make a dragon move to the sky
     */
    void targetSky();

    /**
     * Make a dragon move to a specific player
     */
    void targetPlayer(Player player);

    /**
     * Make a dragon move to last specified player
     */
    void targetPlayer();

    /**
     * Set a dragon AFK
     *
     * @param afk boolean
     */
    void setAfk(boolean afk);

    /**
     * Teleport dragon to a location
     *
     * @param location Location to be teleported
     */
    void teleport(Location location);

    /**
     * Kill Dragon
     */
    void kill();

    /**
     * @return EnderDragon Entity
     */
    EnderDragon dragon();

    enum TargetType {
        SKY, PLAYER;
    }

    enum TargetCause {
        HIT, TIMEOUT;
    }
}
