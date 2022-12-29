package com.qualityplus.minions.base.handler;

import com.qualityplus.assistant.hologram.TheHologram;
import com.qualityplus.assistant.util.armorstand.ArmorStandUtil;
import com.qualityplus.assistant.util.random.RandomSelector;
import com.qualityplus.minions.TheMinions;
import com.qualityplus.minions.api.handler.ArmorStandHandler;
import com.qualityplus.minions.api.minion.MinionEntity;
import com.qualityplus.minions.base.minions.entity.message.RandomMessage;
import com.qualityplus.minions.base.minions.entity.state.MinionState;
import com.qualityplus.minions.base.minions.entity.status.MinionStatus;
import com.qualityplus.minions.base.minions.entity.tracker.ArmorStandTracker;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public final class ArmorStandHandlerImpl implements ArmorStandHandler {
    private TheHologram hologram;
    @Getter
    private ArmorStand entity;
    private Location spawn;

    @Override
    public CompletableFuture<ArmorStand> createEntity(MinionEntity minionEntity, Location spawn) {
        CompletableFuture<ArmorStand> future = new CompletableFuture<>();

        this.spawn = spawn;

        Bukkit.getScheduler().runTask(TheMinions.getInstance(), () -> {
            this.entity = ArmorStandUtil.createDefault(spawn);

            ArmorStandTracker.registerNewEntity(entity, minionEntity);

            future.complete(entity);
        });

        return future;
    }

    @Override
    public boolean entityIsValid() {
        return ArmorStandUtil.entityIsValid(entity);
    }

    @Override
    public void removeEntity() {
        Optional.ofNullable(entity)
                .filter(ArmorStandUtil::entityIsValid)
                .ifPresent(e -> {
                    ArmorStandTracker.unregisterEntity(e);
                    e.remove();
                });

        Optional.ofNullable(hologram)
                .filter(Objects::nonNull)
                .ifPresent(TheHologram::remove);
    }

    @Override
    public void teleportToSpawn() {
        if(!entityIsValid()) return;

        entity.teleport(spawn);
    }

    @Override
    public void updateDisplayName(MinionState minionState) {
        MinionStatus status = minionState.getStatus();
        MinionStatus oldStatus = minionState.getOldStatus();

        if(status == null || (oldStatus != null && oldStatus.equals(status)))
            return;

        List<RandomMessage> randomMessages = TheMinions.getApi().getConfigFiles().config().messages.getOrDefault(status, null);

        RandomMessage randomSelector = new RandomSelector<>(randomMessages).getRandomModified();

        List<String> msg = Optional.ofNullable(randomSelector).map(RandomMessage::getMessage).orElse(Collections.singletonList("       "));

        //TODO check this with a future or boolean
        Bukkit.getScheduler().runTask(TheMinions.getInstance(), () -> {
            hologram = Optional.ofNullable(hologram)
                    .map(holoExists -> hologram.rename(msg))
                    .orElse(TheHologram.create(msg, spawn.clone()));
        });


        minionState.setOldStatus(status);
    }

    @Override
    public void manipulateArmorStand(Consumer<ArmorStand> consumer) {
        consumer.accept(entity);
    }
}
