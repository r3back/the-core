package com.qualityplus.dragon.base.service;

import com.qualityplus.dragon.api.factory.GuardianFactory;
import com.qualityplus.dragon.api.game.guardian.Guardian;
import com.qualityplus.dragon.api.service.GuardianService;
import com.qualityplus.dragon.api.service.GuardianSpawnFactory;
import com.qualityplus.dragon.base.configs.DragonEventsFile.GuardianChanceConfig;

import eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;
import org.bukkit.Location;

import java.util.List;

@Component
public final class GuardianServiceImpl implements GuardianService {
    private @Inject @Getter GuardianSpawnFactory spawnFactory;
    private @Inject @Getter GuardianFactory factory;

    @Override
    public Guardian getRandomGuardian(List<GuardianChanceConfig> guardianChanceConfigs) {
        return factory.getRandom(guardianChanceConfigs);
    }

    @Override
    public Location getRandomLocation() {
        return spawnFactory.getRandomLocation();
    }
}
