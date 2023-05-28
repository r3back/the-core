package com.qualityplus.alchemist.api.session;

import org.bukkit.Location;

import java.util.UUID;

/**
 * Stand session
 */
public interface StandSession {
    /**
     * Retrieves session location
     *
     * @return {@link Location}
     */
    public Location getLocation();

    /**
     * Retrieves player's session uuid
     *
     * @return {@link UUID}
     */
    public UUID getPlayer();
}
