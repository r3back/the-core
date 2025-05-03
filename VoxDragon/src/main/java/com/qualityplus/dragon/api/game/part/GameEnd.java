package com.qualityplus.dragon.api.game.part;

import com.qualityplus.dragon.base.game.player.EventPlayer;

import java.util.List;

/**
 * Implementation for the final phase of a DragonGame
 */
public interface GameEnd {
    /**
     * Send a Message when game finish
     */
    void sendFinishMessage(final List<EventPlayer> players);
}
