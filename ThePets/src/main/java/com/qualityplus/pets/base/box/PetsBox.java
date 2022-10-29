package com.qualityplus.pets.base.box;

import com.qualityplus.pets.api.box.Box;
import com.qualityplus.pets.api.config.ConfigFiles;
import com.qualityplus.pets.api.service.PetsService;
import com.qualityplus.pets.base.config.*;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

@Component
public final class PetsBox implements Box {
    private @Inject ConfigFiles<Config, Inventories, Messages, Commands> files;
    private @Inject PetsService petsService;
    private @Inject Plugin plugin;

    @Override
    public ConfigFiles<Config, Inventories, Messages, Commands> files() {
        return files;
    }

    @Override
    public PetsService service() {
        return petsService;
    }

    @Override
    public Plugin plugin() {
        return plugin;
    }
}
