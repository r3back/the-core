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
import com.qualityplus.minions.base.minions.entity.tracker.MinionArmorStandTracker;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public final class ArmorStandHandlerImpl implements ArmorStandHandler {
    private TheHologram hologram;
    private ArmorStand entity;
    private Location spawn;

    @Override
    public CompletableFuture<ArmorStand> createEntity(MinionEntity minionEntity, Location spawn) {
        CompletableFuture<ArmorStand> future = new CompletableFuture<>();

        this.spawn = spawn;

        Bukkit.getScheduler().runTask(TheMinions.getInstance(), () -> {
            this.entity = ArmorStandUtil.createDefault(spawn);

            MinionArmorStandTracker.registerNewEntity(entity, minionEntity);

            future.complete(entity);
        });

        return future;
    }

    @Override
    public boolean entityIsValid() {
        return ArmorStandUtil.entityIsValid(entity);
    }

    @Override
    public Location getLocation() {
        return Optional.ofNullable(entity)
                .map(Entity::getLocation)
                .map(Location::clone)
                .orElse(null);
    }

    @Override
    public void removeEntity() {

        Optional.ofNullable(entity)
                .filter(ArmorStandUtil::entityIsValid)
                .ifPresent(e -> {
                    final UUID uuid = e.getUniqueId();
                    MinionArmorStandTracker.unregisterEntity(uuid);
                    //Bukkit.getConsoleSender().sendMessage("REMOVING ENTITY");
                    e.remove();
                });

        Optional.ofNullable(hologram)
                .filter(Objects::nonNull)
                .ifPresent(TheHologram::remove);
    }

    @Override
    public void teleportToSpawn() {
        if (!entityIsValid()) return;

        entity.teleport(spawn);
    }

    @Override
    public void updateDisplayName(MinionState minionState) {
        final MinionStatus status = minionState.getStatus();
        final MinionStatus oldStatus = minionState.getOldStatus();

        if (status == null || (oldStatus != null && oldStatus.equals(status))) {
            return;
        }

        final List<RandomMessage> randomMessages = TheMinions.getApi().getConfigFiles().config().messages.getOrDefault(status, null);

        final RandomMessage randomSelector = new RandomSelector<>(randomMessages).getRandomOrUniqueItem();

        final List<String> msg = Optional.ofNullable(randomSelector).map(RandomMessage::getMessage).orElse(Collections.singletonList("&cInvalid Error!"));

        //TODO check this with a future or boolean
        Bukkit.getScheduler().runTask(TheMinions.getInstance(), () -> {

            if (hologram == null) {
                hologram = TheHologram.create(msg, spawn.clone());
            } else {
                hologram.rename(msg);
            }
        });


        minionState.setOldStatus(status);
    }

    @Override
    public void manipulateEntity(Consumer<ArmorStand> consumer) {
        if (!entityIsValid()) return;

        consumer.accept(entity);
    }
}
