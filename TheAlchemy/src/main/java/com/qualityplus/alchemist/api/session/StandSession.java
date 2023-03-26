package com.qualityplus.alchemist.api.session;

import org.bukkit.Location;

import java.util.UUID;

public interface StandSession {
    Location getLocation();

    UUID getPlayer();
}
