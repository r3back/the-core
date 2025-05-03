package com.qualityplus.souls.listener;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import com.qualityplus.souls.VoxSouls;
import com.qualityplus.souls.api.box.Box;
import com.qualityplus.souls.api.service.SoulsService;
import com.qualityplus.souls.base.soul.Soul;
import org.bukkit.Bukkit;
import org.bukkit.ChunkSnapshot;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public final class ChunkListener implements Listener {
    private final Map<ChunkSnapshot, List<Soul>> toLoadEntities = new HashMap<>();
    private @Inject SoulsService service;
    private @Inject Box box;


    @EventHandler
    public void onChunkLoad(final ChunkUnloadEvent event) {
        final Entity[] entities = event.getChunk().getEntities();

        if (entities == null || entities.length < 1) {
            return;
        }

        for (final Entity entity : entities) {
            final Optional<Soul> soul = Optional.ofNullable(Soul.SOUL_REGISTRY.getOrDefault(entity.getUniqueId(), null));
            if (soul.isEmpty()) {
                continue;
            }
            soul.get().disable();
            final ChunkSnapshot snapshot = event.getChunk().getChunkSnapshot();
            final List<Soul> souls = this.toLoadEntities.getOrDefault(snapshot, new ArrayList<>());
            souls.add(soul.get());
            this.toLoadEntities.put(snapshot, souls);
        }
    }

    @EventHandler
    public void onChunkUnload(final ChunkUnloadEvent event) {

        Bukkit.getScheduler().runTaskAsynchronously(VoxSouls.getApi().getPlugin(), () -> {
            final ChunkSnapshot snapshot = event.getChunk().getChunkSnapshot();
            final List<Soul> souls = this.toLoadEntities.getOrDefault(snapshot, new ArrayList<>());
            if (souls.isEmpty()) {
                return;
            }
            loadAndBlock(snapshot, souls);
        });


    }

    public synchronized void loadAndBlock(final ChunkSnapshot chunkSnapshot, final List<Soul> souls) {
        if (souls.isEmpty()) {
            return;
        }

        souls.forEach(soul -> soul.enable(this.box, false));
        souls.forEach(souls::remove);
        toLoadEntities.remove(chunkSnapshot);
    }
}
