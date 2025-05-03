package com.qualityplus.trades.base.box;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import com.qualityplus.trades.api.box.Box;
import com.qualityplus.trades.api.config.ConfigFiles;
import com.qualityplus.trades.api.service.TradesService;
import com.qualityplus.trades.base.config.Commands;
import com.qualityplus.trades.base.config.Config;
import com.qualityplus.trades.base.config.Inventories;
import com.qualityplus.trades.base.config.Messages;
import com.qualityplus.trades.base.config.TradesFile;
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
