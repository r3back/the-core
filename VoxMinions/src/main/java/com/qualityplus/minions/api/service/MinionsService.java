package com.qualityplus.minions.api.service;

import com.qualityplus.assistant.api.service.DataManagementService;
import com.qualityplus.minions.persistance.data.MinionData;

import java.util.List;
import java.util.UUID;

public interface MinionsService extends DataManagementService<MinionData> {
    List<UUID> getAllKeys();
}
