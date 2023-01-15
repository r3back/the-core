package com.qualityplus.minions.api.handler;

import com.qualityplus.minions.api.minion.MinionEntity;
import com.qualityplus.minions.base.minions.entity.state.MinionState;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface ArmorStandHandler {
    @Nullable
    Location getLocation();
    void removeEntity();
    void teleportToSpawn();
    boolean entityIsValid();
    void updateDisplayName(MinionState minionState);
    void manipulateEntity(Consumer<ArmorStand> consumer);
    CompletableFuture<ArmorStand> createEntity(MinionEntity minionEntity, Location location);
}
