package com.qualityplus.dragon.api.game.part;

import com.qualityplus.assistant.api.addons.paster.session.PasterSession;

import java.util.concurrent.CompletableFuture;

/**
 * Handles Block Explosion during a game
 */
public interface GameExplosion {
    /**
     *
     * @return When Block explosion is finished
     */
    CompletableFuture<Void> makeBlockExplosion(PasterSession session);
}
