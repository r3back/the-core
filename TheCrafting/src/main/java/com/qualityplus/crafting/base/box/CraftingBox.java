package com.qualityplus.crafting.base.box;

import com.qualityplus.crafting.api.box.Box;
import com.qualityplus.crafting.api.config.ConfigFiles;
import com.qualityplus.crafting.base.config.*;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

@Component
public final class CraftingBox implements Box {
    private @Inject ConfigFiles<Config, RecipesFile, Inventories, Messages, Commands, Categories> files;
    private @Inject Plugin plugin;

    @Override
    public ConfigFiles<Config, RecipesFile, Inventories, Messages, Commands, Categories> files() {
        return files;
    }

    @Override
    public Plugin plugin() {
        return plugin;
    }
}
