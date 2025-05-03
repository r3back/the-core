package com.qualityplus.auction.base.config.impl;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.auction.api.config.ConfigFiles;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import com.qualityplus.auction.base.config.Categories;
import com.qualityplus.auction.base.config.Commands;
import com.qualityplus.auction.base.config.Config;
import com.qualityplus.auction.base.config.Inventories;
import com.qualityplus.auction.base.config.Messages;

/**
 * Utility class for implements config files
 */
@Component
public final class AlchemyFiles implements ConfigFiles<Config, Inventories, Messages, Commands, Categories> {
    private @Inject Inventories inventories;
    private @Inject Categories bankUpgrades;
    private @Inject Messages messages;
    private @Inject Commands commands;
    private @Inject Config config;

    @Override
    public Config config() {
        return this.config;
    }

    @Override
    public Categories bankUpgrades() {
        return this.bankUpgrades;
    }

    @Override
    public Inventories inventories() {
        return this.inventories;
    }

    @Override
    public Messages messages() {
        return this.messages;
    }

    @Override
    public Commands commands() {
        return this.commands;
    }

    @Override
    public void reloadFiles() {
        this.config.load();
        this.bankUpgrades.load();
        this.messages.load();
        this.inventories.load();
        this.commands.load();
    }
}
