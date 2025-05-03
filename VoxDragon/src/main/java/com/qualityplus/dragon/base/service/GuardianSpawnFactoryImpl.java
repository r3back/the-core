package com.qualityplus.dragon.base.service;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.util.location.ALocation;
import com.qualityplus.assistant.util.random.RandomSelector;
import com.qualityplus.assistant.util.random.RandomUtil;
import com.qualityplus.dragon.api.service.GuardianSpawnFactory;
import com.qualityplus.dragon.base.configs.DragonGuardiansFile;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Location;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public final class GuardianSpawnFactoryImpl implements GuardianSpawnFactory {
    private @Inject DragonGuardiansFile dragonGuardiansFile;

    @Override
    public ALocation getRandomLocation() {
        final List<ALocation> locations = Optional.ofNullable(dragonGuardiansFile.guardianSpawns)
                .orElse(Collections.emptyList());

        return !locations.isEmpty() ? RandomUtil.getRandom(locations) : null;
    }
}
