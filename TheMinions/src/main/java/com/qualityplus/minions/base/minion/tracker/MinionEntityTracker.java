package com.qualityplus.minions.base.minion.tracker;

import com.google.common.collect.ImmutableSet;
import com.qualityplus.minions.api.minion.entity.MinionEntity;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Scheduled;
import eu.okaeri.platform.core.annotation.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class MinionEntityTracker {
    private static final Map<UUID, MinionEntity> TRACKED_ENTITIES = new HashMap<>();

    @ApiStatus.Internal
    public static void registerNewEntity(@NotNull final MinionEntity petEntity) {
        TRACKED_ENTITIES.put(petEntity.getPetUniqueId(), petEntity);
    }


    @ApiStatus.Internal
    public static void unregisterEntity(@NotNull final MinionEntity petEntity) {
        TRACKED_ENTITIES.remove(petEntity.getPetUniqueId());
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

    @Component
    public final class MinionTickServiceImpl implements MinionTickService {
        @Override
        public void follow(Map.Entry<UUID, MinionEntity> entry){
            entry.getValue().tickMinion();
        }
    }


    @Component
    private static class TickScheduler{
        @Inject
        private MinionTickService minionTickService;

        @Scheduled(rate = 1)
        private void tickAll(){
            TRACKED_ENTITIES.entrySet().forEach(minionTickService::follow);
        }

    }

    public interface MinionTickService {
        void follow(Map.Entry<UUID, MinionEntity> entry);
    }


}
