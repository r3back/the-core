package com.qualityplus.runes.base.box;

import com.qualityplus.runes.base.config.*;
import com.qualityplus.runes.api.box.Box;
import com.qualityplus.runes.api.config.ConfigFiles;
import com.qualityplus.runes.api.service.RunesService;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

@Component
public final class TradesBox implements Box {
    private @Inject ConfigFiles<Config, RunesFile, Inventories, Messages, Commands> files;

    private @Inject RunesService tradesService;
    private @Inject Plugin plugin;

    @Override
    public ConfigFiles<Config, RunesFile, Inventories, Messages, Commands> files() {
        return files;
    }

    @Override
    public RunesService runesService() {
        return tradesService;
    }

    @Override
    public Plugin plugin() {
        return plugin;
    }
}
