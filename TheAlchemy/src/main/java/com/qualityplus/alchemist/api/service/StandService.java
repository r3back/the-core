package com.qualityplus.alchemist.api.service;

import com.qualityplus.alchemist.api.session.StandSession;
import org.bukkit.Location;

import java.util.Optional;
import java.util.UUID;

public interface StandService {
    void addSession(UUID uuid, Location location);

    void removeSession(Location location);

    Optional<StandSession> getSession(Location location);
}
