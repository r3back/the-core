package com.qualityplus.dragon.api.service;

import java.util.UUID;

public interface AltarSetupService {
    boolean playerIsInEditMode(UUID uuid);

    void addPlayer(UUID uuid);

    void removePlayer(UUID uuid);
}
