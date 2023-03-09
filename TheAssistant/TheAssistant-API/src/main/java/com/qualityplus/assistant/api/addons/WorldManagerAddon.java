package com.qualityplus.assistant.api.addons;

import com.qualityplus.assistant.api.addons.response.ChunkCheckResponse;
import com.qualityplus.assistant.api.dependency.DependencyPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface WorldManagerAddon extends DependencyPlugin {
    default List<Entity> getChunkEntities(Location location){
        return Arrays.asList(location.getChunk().getEntities());
    }

    default CompletableFuture<ChunkCheckResponse> chunksAreLoaded(Location location){
        return CompletableFuture.completedFuture(ChunkCheckResponse.builder()
                .canBeLoaded(true)
                .areLoaded(location.getChunk().isLoaded())
                .build());
    }

    default void loadChunks(Location location){
        location.getChunk().load();
    }

    default void setup(){}
}
