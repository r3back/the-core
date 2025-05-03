package com.qualityplus.anvil.base.config.impl;

import com.qualityplus.anvil.api.config.ConfigFiles;
import com.qualityplus.anvil.base.config.*;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;

@Component
public final class AnvilFiles implements ConfigFiles<Config, Inventories, Messages, Commands> {
    private @Inject Inventories inventories;
    private @Inject Messages messages;
    private @Inject Commands commands;
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
        messages.load();
        inventories.load();
        commands.load();
    }
}
