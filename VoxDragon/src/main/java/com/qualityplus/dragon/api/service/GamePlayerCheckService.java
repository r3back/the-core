package com.qualityplus.dragon.api.service;

/**
 * Interface to handle players during dragon game
 */
public interface GamePlayerCheckService {
    /**
     * Starts checking for in-game players
     */
    public void startChecking();

    /**
     * Stops checking process
     */
    public void stopChecking();
}
