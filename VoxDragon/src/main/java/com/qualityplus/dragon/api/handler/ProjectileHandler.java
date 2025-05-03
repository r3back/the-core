package com.qualityplus.dragon.api.handler;

import com.qualityplus.dragon.api.game.DragonGame;

/**
 * Handle Projectiles During an Event
 */
public interface ProjectileHandler {
    /**
     * Shoot a projectile
     *
     * @param projectile ProjectileType
     * @param damage     Amount of Damage
     * @param amount     Projectile's quantity
     * @param dragonGame The Dragon Game
     */
    void shoot(ProjectileType projectile, double damage, double amount, DragonGame dragonGame);

    enum ProjectileType{
        FIREBALL(),
        DRAGONBALL();
    }
}
