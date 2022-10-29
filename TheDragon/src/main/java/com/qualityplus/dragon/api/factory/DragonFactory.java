package com.qualityplus.dragon.api.factory;

import com.qualityplus.dragon.api.game.dragon.TheDragonEntity;
import com.qualityplus.dragon.base.game.dragon.TheDragonEntityImpl;

import java.util.Map;

/**
 * Dragon Factory
 */
public interface DragonFactory {
    /**
     * Get A dragon to be used during a game
     *
     * @param map Dragons to search in
     * @return TheDragonEntity Selected
     */
    TheDragonEntity getDragon(Map<String, TheDragonEntityImpl> map);
}
