package com.qualityplus.auction.base.config.impl;

import com.qualityplus.auction.api.config.ConfigFiles;
import com.qualityplus.auction.base.config.*;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;

@Component
public final class AlchemyFiles implements ConfigFiles<Config, Inventories, Messages, Commands, Categories> {
    private @Inject Inventories inventories;
    private @Inject
    Categories bankUpgrades;
    private @Inject Messages messages;
    private @Inject Commands commands;
    private @Inject Config config;

    @Override
    public Config config() {
        return config;
    }

    @Override
    public Categories bankUpgrades() {
        return bankUpgrades;
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
        bankUpgrades.load();
        messages.load();
        inventories.load();
        commands.load();
    }
}
