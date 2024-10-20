package com.qualityplus.dragon.api.handler;

import com.qualityplus.assistant.lib.com.georgev22.particle.ParticleEffect;
import com.qualityplus.dragon.api.game.DragonGame;

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
    public void spell(final DragonGame dragonGame, final ParticleEffect particle);
}
