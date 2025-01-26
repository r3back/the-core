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
        return null;
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
    public void updateDisplayName(final MinionState minionState) {

    }

    @Override
    public void manipulateEntity(Consumer<ArmorStand> consumer) {
        if (!entityIsValid()) return;

        consumer.accept(entity);
    }
}
