package com.qualityplus.minions.base.minions.entity.scheduler;

import com.qualityplus.minions.api.minion.MinionEntity;

import java.util.Map;
import java.util.UUID;

public interface MinionTickService {
    void tick(Map.Entry<UUID, MinionEntity> entry);
}