package com.qualityplus.trades.base.config.impl;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import com.qualityplus.trades.api.config.ConfigFiles;
import com.qualityplus.trades.base.config.Commands;
import com.qualityplus.trades.base.config.Config;
import com.qualityplus.trades.base.config.Inventories;
import com.qualityplus.trades.base.config.Messages;
import com.qualityplus.trades.base.config.TradesFile;

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
