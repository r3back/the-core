package com.qualityplus.collections.api.service;

import com.qualityplus.collections.base.collection.Collection;
import com.qualityplus.collections.persistance.data.UserData;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public interface CollectionsService {
    Optional<UserData> getCollectionsData(UUID uuid);

    void addData(UserData data);

    void removeData(UserData data);

    void addXp(Player player, Collection collection, double xp);
}
