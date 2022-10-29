package com.qualityplus.skills.api.service;

import org.bukkit.entity.Player;

import java.util.UUID;

public interface ActionBarService {
    void sendActionBar(Player player, String message);

    void blacklist(UUID uuid);

    boolean isBlacklisted(UUID uuid);

    void whitelistTemp(UUID uuid);

    boolean isWhitelisted(UUID uuid);
}
