package com.qualityplus.minions.base.service;

import com.qualityplus.minions.api.service.UserService;
import com.qualityplus.minions.persistance.data.UserData;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public final class UserServiceImpl implements UserService {
    private final Map<UUID, UserData> dataMap = new HashMap<>();

    @Override
    public Optional<UserData> getData(UUID uuid) {
        return Optional.ofNullable(dataMap.getOrDefault(uuid, null));
    }

    @Override
    public void addData(UserData data) {
        dataMap.put(data.getUuid(), data);
    }

    @Override
    public void removeData(UserData data) {
        dataMap.remove(data.getUuid());
    }
}
