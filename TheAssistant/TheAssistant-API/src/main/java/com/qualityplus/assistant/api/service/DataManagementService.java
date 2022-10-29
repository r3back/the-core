package com.qualityplus.assistant.api.service;

import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public interface DataManagementService<T> {
    default Optional<T> getData(Player player){
        return getData(player.getUniqueId());
    }

    Optional<T> getData(UUID uuid);

    void addData(T data);

    void removeData(T data);
}
