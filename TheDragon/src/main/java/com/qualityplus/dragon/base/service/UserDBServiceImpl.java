package com.qualityplus.dragon.base.service;

import com.qualityplus.dragon.api.service.UserDBService;
import com.qualityplus.dragon.persistance.data.UserData;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public final class UserDBServiceImpl implements UserDBService {
    private final Map<UUID, UserData> dataMap = new HashMap<>();

    @Override
    public Optional<UserData> getDragonData(UUID uuid) {
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
