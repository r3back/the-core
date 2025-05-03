package com.qualityplus.dragon.api.service;

import com.google.common.collect.ImmutableList;
import com.qualityplus.dragon.base.game.player.EventPlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Handles Users during a game session
 */
public interface UserService {
    /**
     * Send a message to an specific player in the session
     *
     * @param eventUser EventUser to receive message
     * @param message Message to be sent
     */
    public void sendMessage(final EventPlayer eventUser, final List<String> message);

    /**
     * Send a message to all players in the session
     *
     * @param message Message to be sent
     */
    public void sendMessage(final List<String> message);

    /**
     * Send Finish message to all players in the session
     */
    public void sendFinishMessage();

    /**
     * Reset Data from current session
     */
    public void resetData();

    /**
     * Add
     * @param player Player
     * @param damage Damage Amount
     */
    public void addPlayerDamage(final Player player, final double damage);

    /**
     * Method to remove damage from player count when dragon
     * regen its life
     *
     * @param damage Damage Amount
     */
    public void removePlayersDamage(final double damage);

    /**
     * Method to set last player who damaged dragon
     *
     * @param uuid UUID
     */
    public void setLast(final UUID uuid);

    /**
     *
     * @return Last user who hit dragon
     */
    public UUID getLast();

    /**
     *
     * @return All Game Users
     */
    public ImmutableList<EventPlayer> getUsers();

    /**
     * Add player to users list
     *
     * @param player {@link EventPlayer}
     */
    public void addPlayer(final EventPlayer player);

    /**
     * Remove player from users list
     *
     * @param player {@link EventPlayer}
     */
    public void removePlayer(final EventPlayer player);

    /**
     * Retrieve Player from Dragon Event
     *
     * @param uuid {@link UUID}
     * @return Optional of {@link EventPlayer}
     */
    public Optional<EventPlayer> getByUUID(final UUID uuid);
}
