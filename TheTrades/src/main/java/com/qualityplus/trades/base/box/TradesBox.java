package com.qualityplus.trades.base.box;

import com.qualityplus.trades.api.box.Box;
import com.qualityplus.trades.api.config.ConfigFiles;
import com.qualityplus.trades.api.service.TradesService;
import com.qualityplus.trades.base.config.*;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

@Component
public final class TradesBox implements Box {
    private @Inject ConfigFiles<Config, TradesFile, Inventories, Messages, Commands> files;

    private @Inject TradesService tradesService;
    private @Inject Plugin plugin;

    @Override
    public ConfigFiles<Config, TradesFile, Inventories, Messages, Commands> files() {
        return files;
    }

    @Override
    public TradesService tradesService() {
        return tradesService;
    }

    @Override
    public Plugin plugin() {
        return plugin;
    }
}
