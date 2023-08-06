package com.qualityplus.skills.api.service;

import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Utility class for action bar service
 */
public interface ActionBarService {
    /**
     * Adds an action bar
     *
     * @param player  {@link Player}
     * @param message Message
     */
    public void sendActionBar(Player player, String message);

    /**
     * Adds a uuid
     *
     * @param uuid {@link UUID}
     */
    public void blacklist(UUID uuid);

    /**
     * Adds a uuid
     *
     * @param uuid {@link UUID}
     * @return     Is Blacklisted
     */
    public boolean isBlacklisted(UUID uuid);

    /**
     * Adds a uuid
     *
     * @param uuid {@link UUID}
     */
    public void whitelistTemp(UUID uuid);

    /**
     * Adds a UUID
     *
     * @param uuid {@link UUID}
     * @return     Is Whitelisted
     */
    public boolean isWhitelisted(UUID uuid);
}
