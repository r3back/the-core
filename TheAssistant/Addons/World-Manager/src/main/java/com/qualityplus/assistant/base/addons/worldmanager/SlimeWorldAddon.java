package com.qualityplus.assistant.base.addons.worldmanager;

import com.grinderwolf.swm.api.SlimePlugin;
import com.grinderwolf.swm.api.loaders.SlimeLoader;
import com.grinderwolf.swm.api.world.SlimeWorld;
import com.grinderwolf.swm.api.world.properties.SlimePropertyMap;
import com.qualityplus.assistant.api.addons.WorldManagerAddon;
import com.qualityplus.assistant.api.addons.response.ChunkCheckResponse;
import com.qualityplus.assistant.api.config.ConfigSlimeWorldManager;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class SlimeWorldAddon implements WorldManagerAddon {
    private static final SlimePropertyMap PROPERTIES = new SlimePropertyMap();
    private @Inject ConfigSlimeWorldManager configSlimeWorldManager;
    private SlimePlugin slimePlugin;
    private SlimeLoader loader;


    @Override
    public List<Entity> getChunkEntities(Location location) {
        return null;
    }

    @Override
    public CompletableFuture<ChunkCheckResponse> chunksAreLoaded(Location location) {

        String name = location.getWorld().getName();

        if(Bukkit.getWorld(name) != null){
            return WorldManagerAddon.super.chunksAreLoaded(location);
        }

        SlimeWorld slimeWorld = slimePlugin.getWorld(loader, location.getWorld().getName());

        if(slimeWorld == null){
            return CompletableFuture.completedFuture(ChunkCheckResponse.builder()
                    .areLoaded(false)
                    .canBeLoaded(false)
                    .build());
        }


        CompletableFuture<ChunkCheckResponse> response = new CompletableFuture<>();

        slimePlugin.asyncLoadWorld(loader, name, false, PROPERTIES).thenAccept(world -> {
            if(!world.isPresent()) return;

            response.complete(ChunkCheckResponse.builder()
                    .areLoaded(true)
                    .canBeLoaded(true)
                    .build());
        });

        return response;
    }

    @Override
    public void loadChunks(Location location) {

    }

    @Override
    public void setup() {
        try {
            this.slimePlugin = (SlimePlugin) Bukkit.getPluginManager().getPlugin("SlimeWorldManager");

            if(slimePlugin == null)
                throw new RuntimeException();

            this.loader = slimePlugin.getLoader(configSlimeWorldManager.getSlimeWorldManagerSource());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public String getAddonName() {
        return "Slime World Manager";
    }
}
