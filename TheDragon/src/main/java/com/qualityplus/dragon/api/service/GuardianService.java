package com.qualityplus.dragon.api.service;

import com.qualityplus.dragon.api.game.guardian.Guardian;
import com.qualityplus.dragon.base.configs.DragonEventsFile.GuardianChanceConfig;
import org.bukkit.Location;

import java.util.List;

public interface GuardianService {
    Guardian getRandomGuardian(List<GuardianChanceConfig> guardianChanceConfigs);

    Location getRandomLocation();
}
