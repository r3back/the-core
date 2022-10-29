package com.qualityplus.dragon.base.service;

import com.qualityplus.dragon.api.service.AltarSetupService;
import eu.okaeri.platform.core.annotation.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public final class AltarSetupServiceImpl implements AltarSetupService {
    private final Set<UUID> players = new HashSet<>();

    @Override
    public boolean playerIsInEditMode(UUID uuid) {
        return players.contains(uuid);
    }

    @Override
    public void addPlayer(UUID uuid) {
        players.add(uuid);
    }

    @Override
    public void removePlayer(UUID uuid) {
        players.remove(uuid);
    }
}
