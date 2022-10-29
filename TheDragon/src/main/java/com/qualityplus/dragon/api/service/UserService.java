package com.qualityplus.dragon.api.service;

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
    void sendMessage(EventPlayer eventUser, List<String> message);

    /**
     * Send a message to all players in the session
     *
     * @param message Message to be sent
     */
    void sendMessage(List<String> message);

    /**
     * Send Finish message to all players in the session
     */
    void sendFinishMessage();

    /**
     * Reset Data from current session
     */
    void resetData();

    /**
     * Add
     * @param player Player
     * @param damage Damage Amount
     */
    void addPlayerDamage(Player player, double damage);

    /**
     * Method to remove damage from player count when dragon
     * regen its life
     *
     * @param damage Damage Amount
     */
    void removePlayersDamage(double damage);

    /**
     * Method to set last player who damaged dragon
     *
     * @param uuid UUID
     */
    void setLast(UUID uuid);

    /**
     *
     * @return Last user who hit dragon
     */
    UUID getLast();

    /**
     * Send BossBar during Game
     */
    void startBossBar();

    /**
     * Stop BossBar during Game
     */
    void stopBossBar();

    /**
     *
     * @return All Game Users
     */
    List<EventPlayer> getUsers();

    Optional<EventPlayer> getByUUID(UUID uuid);
}
