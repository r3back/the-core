package com.qualityplus.minions.api.handler;

import com.qualityplus.minions.base.minions.entity.MinionStorageState;

import java.util.concurrent.CompletableFuture;

public interface StorageHandler {
    MinionStorageState getMinionStorageState();
}
