package com.qualityplus.skills.base.config.impl;

import com.qualityplus.skills.api.config.ConfigFiles;
import com.qualityplus.skills.base.config.Commands;
import com.qualityplus.skills.base.config.Config;
import com.qualityplus.skills.base.config.Inventories;
import com.qualityplus.skills.base.config.Messages;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;

@Component
public final class BasicFiles implements ConfigFiles<Config, Inventories, Commands, Messages> {
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
