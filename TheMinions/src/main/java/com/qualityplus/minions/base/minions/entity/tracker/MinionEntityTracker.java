package com.qualityplus.minions.base.minions.entity.tracker;

import com.google.common.collect.ImmutableSet;
import com.qualityplus.minions.api.minion.MinionEntity;

import eu.okaeri.platform.bukkit.annotation.Scheduled;
import eu.okaeri.platform.core.annotation.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class MinionEntityTracker {
    /**
     * TODO MAKE THIS PRIVATE
     */
    public static final Map<UUID, MinionEntity> TRACKED_ENTITIES = new HashMap<>();

    @ApiStatus.Internal
    public static void registerNewEntity(@NotNull final MinionEntity minionEntity) {
        TRACKED_ENTITIES.put(minionEntity.getMinionUniqueId(), minionEntity);
    }

    @ApiStatus.Internal
    public static void unregisterEntity(@NotNull final MinionEntity minionEntity) {
        TRACKED_ENTITIES.remove(minionEntity.getMinionUniqueId());
    }

    @NotNull
    public static Optional<MinionEntity> getByID(@NotNull final UUID id) {
        return Optional.ofNullable(TRACKED_ENTITIES.get(id));
    }

    public static Set<MinionEntity> values() {
        return ImmutableSet.copyOf(TRACKED_ENTITIES.values());
    }

    public static Set<MinionEntity> values(Predicate<MinionEntity> filter) {
        return ImmutableSet.copyOf(TRACKED_ENTITIES.values().stream().filter(filter).collect(Collectors.toList()));
    }
}
