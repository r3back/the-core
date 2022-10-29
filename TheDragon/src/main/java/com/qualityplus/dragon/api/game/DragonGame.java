package com.qualityplus.dragon.api.game;

import com.qualityplus.dragon.api.exception.check.NoSpawnException;
import com.qualityplus.dragon.api.exception.check.NoStructureException;

/**
 * The Dragon Game Implementation
 */
public interface DragonGame {
    /**
     * Start Dragon Game
     */
    void start();

    /**
     * Finish Dragon Game
     */
    void finish();

    /**
     * Check if game cant start
     */
    boolean canStart() throws NoSpawnException, NoStructureException;

    /**
     *
     * @return If Game is in progress
     */
    boolean isActive();
}
