package com.qualityplus.minions.base.minions.entity.tracker;

import com.qualityplus.minions.api.minion.MinionEntity;
import org.bukkit.entity.ArmorStand;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class MinionArmorStandTracker {
    private static final Map<UUID, MinionEntity> TRACKED_ENTITIES = new HashMap<>();

    @ApiStatus.Internal
    public static void registerNewEntity(@NotNull final ArmorStand armorStand, MinionEntity minionEntity) {
        TRACKED_ENTITIES.put(armorStand.getUniqueId(), minionEntity);
    }


    @ApiStatus.Internal
    public static void unregisterEntity(@NotNull final ArmorStand armorStand) {
        TRACKED_ENTITIES.remove(armorStand.getUniqueId());
    }

    @ApiStatus.Internal
    public static void unregisterEntity(@NotNull final UUID armorStand) {
        TRACKED_ENTITIES.remove(armorStand);
    }


    @NotNull
    public static Optional<MinionEntity> getByID(@NotNull final UUID id) {
        return Optional.ofNullable(TRACKED_ENTITIES.get(id));
    }
}
