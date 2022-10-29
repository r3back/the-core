package com.qualityplus.anvil.base.box;

import com.qualityplus.anvil.api.box.Box;
import com.qualityplus.anvil.api.config.ConfigFiles;
import com.qualityplus.anvil.base.config.*;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

@Component
public final class EnchantingBox implements Box {
    private @Inject ConfigFiles<Config, RecipesFile, Inventories, Messages, Commands> files;
    private @Inject Plugin plugin;

    @Override
    public ConfigFiles<Config, RecipesFile, Inventories, Messages, Commands> files() {
        return files;
    }

    @Override
    public Plugin plugin() {
        return plugin;
    }
}
