package com.qualityplus.dragon.api.event;

/**
 * Handle Events Scheduler during games
 */
public interface SwitchableEvents {
    /**
     *
     * @return Next DragonEvent
     */
    DragonGameEvent getNext();

    /**
     *
     * @return DragonEvent in Progress
     */
    DragonGameEvent getCurrentEvent();
}
