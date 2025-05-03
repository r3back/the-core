package com.qualityplus.alchemist.api.service;

import com.qualityplus.alchemist.api.session.StandSession;
import org.bukkit.Location;

import java.util.Optional;
import java.util.UUID;

/**
 * Interface to handle brewing stand sessions
 */
public interface StandService {
    /**
     * Adds a session by uuid and location
     *
     * @param uuid     {@link UUID}
     * @param location {@link Location}
     */
    public void addSession(final UUID uuid, final Location location);

    /**
     * Removes a session by location
     *
     * @param location {@link Location}
     */
    public void removeSession(final Location location);

    /**
     * Gets a Stand by location
     *
     * @param location {@link Location}
     * @return Optional of {@link StandSession}
     */
    public Optional<StandSession> getSession(final Location location);
}
