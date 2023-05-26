package com.qualityplus.dragon.api.game;

import com.qualityplus.dragon.api.exception.check.NoSpawnException;
import com.qualityplus.dragon.api.exception.check.NoStructureException;
import com.qualityplus.dragon.base.game.player.EventPlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.Predicate;

/**
 * The Dragon Game Implementation
 */
public interface DragonGame {
    /**
     * Start Dragon Game
     */
    public void start();

    /**
     * Finish Dragon Game
     */
    public void finish();

    /**
     *
     * @throws NoSpawnException     Exception if spawn is not found
     * @throws NoStructureException Exception if any structure is not found
     * @return True if game can start
     */
    public boolean canStart() throws NoSpawnException, NoStructureException;

    /**
     *
     * @return If Game is in progress
     */
    public boolean isActive();

    /**
     * Return players in the game
     *
     * @return List of {@link Player}
     */
    public List<Player> getPlayers();

    /**
     * Return players in the game with custom filter
     *
     * @param filter Predicate of {@link EventPlayer}
     * @return List of {@link Player}
     */
    public List<Player> getPlayers(final Predicate<EventPlayer> filter);
}
