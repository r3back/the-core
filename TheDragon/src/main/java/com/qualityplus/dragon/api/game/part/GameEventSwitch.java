package com.qualityplus.dragon.api.game.part;

import com.qualityplus.dragon.api.event.SwitchableEvents;

/**
 * Handles the Switchable Events during game
 */
public interface GameEventSwitch {
    /**
     * Switch Events
     */
    void switchEvents();

    /**
     * Stop Event Switching
     */
    void stopSwitchEvents();

    /**
     *
     * Handle Events Scheduler during games
     */
    SwitchableEvents getSwitchableEvent();
}
