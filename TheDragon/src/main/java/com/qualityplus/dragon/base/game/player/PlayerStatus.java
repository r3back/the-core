package com.qualityplus.dragon.base.game.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlayerStatus {
    /**
     * Used when player still in dragon's world
     */
    ACTIVE,
    /**
     * Used when a player is not in dragon's world anymore
     */
    INACTIVE
}
