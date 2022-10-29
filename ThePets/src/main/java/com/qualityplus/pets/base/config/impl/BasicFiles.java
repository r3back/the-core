package com.qualityplus.pets.base.config.impl;

import com.qualityplus.pets.api.config.ConfigFiles;
import com.qualityplus.pets.base.config.*;

import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;

@Component
public final class BasicFiles implements ConfigFiles<Config, Inventories, Messages, Commands> {
    private @Inject Inventories inventories;
    private @Inject Commands commands;
    private @Inject Messages messages;
    private @Inject Config config;

    @Override
    public Config config() {
        return config;
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
        commands.load();
        messages.load();
        inventories.load();
    }
}
