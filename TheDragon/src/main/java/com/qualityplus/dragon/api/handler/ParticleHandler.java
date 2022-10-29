package com.qualityplus.dragon.api.handler;

import com.qualityplus.dragon.api.game.DragonGame;
import xyz.xenondevs.particle.ParticleEffect;

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
    void spell(DragonGame dragonGame, ParticleEffect particle);
}
