package com.qualityplus.minions.base.service;

import com.qualityplus.minions.api.service.MinionsService;
import com.qualityplus.minions.persistance.data.MinionData;
import eu.okaeri.platform.core.annotation.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public final class MinionsServiceImpl implements MinionsService {
    private final Map<UUID, MinionData> dataMap = new HashMap<>();

    @Override
    public Optional<MinionData> getData(UUID uuid) {
        return Optional.ofNullable(dataMap.getOrDefault(uuid, null));
    }

    @Override
    public void addData(MinionData data) {
        dataMap.put(data.getUuid(), data);
    }

    @Override
    public void removeData(MinionData data) {
        dataMap.remove(data.getUuid());
    }
}
