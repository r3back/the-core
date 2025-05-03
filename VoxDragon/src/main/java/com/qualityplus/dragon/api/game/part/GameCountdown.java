package com.qualityplus.dragon.api.game.part;

import com.qualityplus.dragon.api.game.dragon.TheDragonEntity;

import java.util.concurrent.CompletableFuture;

/**
 * Dragon Countdown Manager Implementation
 */
public interface GameCountdown {
    /**
     * Start Countdown in a dragon game
     *
     * @param theDragonEntity TheDragonEntity during a game
     * @return When Countdown Finish
     */
    CompletableFuture<Void> start(TheDragonEntity theDragonEntity);
}
