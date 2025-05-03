package com.qualityplus.dragon.api.service;

import com.qualityplus.assistant.util.location.ALocation;
import org.bukkit.Location;

public interface GuardianSpawnFactory {
    ALocation getRandomLocation();
}
