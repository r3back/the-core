package com.qualityplus.skills.base.config.impl;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.skills.api.config.ConfigFiles;
import com.qualityplus.skills.base.config.Commands;
import com.qualityplus.skills.base.config.Config;
import com.qualityplus.skills.base.config.Inventories;
import com.qualityplus.skills.base.config.Messages;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;

/**
 * Utility class for basic files
 */
@Component
public final class BasicFiles implements ConfigFiles<Config, Inventories, Commands, Messages> {
    private @Inject Inventories inventories;
    private @Inject Commands commands;
    private @Inject Messages messages;
    private @Inject Config config;

    @Override
    public Config config() {
        return this.config;
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
        this.commands.load();
        this.messages.load();
        this.inventories.load();
    }
}
