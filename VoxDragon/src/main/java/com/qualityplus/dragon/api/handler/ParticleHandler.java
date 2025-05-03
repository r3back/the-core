package com.qualityplus.dragon.api.handler;

import com.qualityplus.dragon.api.game.DragonGame;
import org.bukkit.Particle;

/**
 * Handle Particles During an Event
 */
public interface ParticleHandler {
    /**
     * Spell Particles
     *
     * @param dragonGame The Dragon Game
     * @param particle   EnumParticle particle type
     */
    public void spell(final DragonGame dragonGame, final Particle particle);
}
