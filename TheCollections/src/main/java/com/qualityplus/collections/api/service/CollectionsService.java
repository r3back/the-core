package com.qualityplus.collections.api.service;

import com.qualityplus.assistant.api.service.DataManagementService;
import com.qualityplus.collections.base.collection.Collection;
import com.qualityplus.collections.persistance.data.UserData;
import org.bukkit.entity.Player;

public interface CollectionsService extends DataManagementService<UserData> {
    void addXp(Player player, Collection collection, double xp);
}
