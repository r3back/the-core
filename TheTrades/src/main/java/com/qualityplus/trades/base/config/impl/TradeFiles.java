package com.qualityplus.trades.base.config.impl;

import com.qualityplus.trades.api.config.ConfigFiles;
import com.qualityplus.trades.base.config.*;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;

@Component
public final class TradeFiles implements ConfigFiles<Config, TradesFile, Inventories, Messages, Commands> {
    private @Inject Inventories inventories;
    private @Inject TradesFile tradesFile;
    private @Inject Messages messages;
    private @Inject Commands commands;
    private @Inject Config config;

    @Override
    public Config config() {
        return config;
    }

    @Override
    public TradesFile trades() {
        return tradesFile;
    }

    @Override
    public Inventories inventories() {
        return inventories;
    }

    @Override
    public Messages messages() {
        return messages;
    }

    @Override
    public Commands commands() {
        return commands;
    }

    @Override
    public void reloadFiles() {
        config.load();
        tradesFile.load();
        messages.load();
        inventories.load();
        commands.load();
    }
}
