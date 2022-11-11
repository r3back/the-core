package com.qualityplus.minions.base.service;

import com.qualityplus.minions.api.service.SoulsService;
import com.qualityplus.minions.persistance.data.SoulsData;
import eu.okaeri.platform.core.annotation.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public final class SoulsServiceImpl implements SoulsService {
    private final Map<UUID, SoulsData> dataMap = new HashMap<>();

    @Override
    public Optional<SoulsData> getData(UUID uuid) {
        return Optional.ofNullable(dataMap.getOrDefault(uuid, null));
    }

    @Override
    public void addData(SoulsData data) {
        dataMap.put(data.getUuid(), data);
    }

    @Override
    public void removeData(SoulsData data) {
        dataMap.remove(data.getUuid());
    }

}
