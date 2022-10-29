package com.qualityplus.runes.base.config.impl;

import com.qualityplus.runes.base.config.*;
import com.qualityplus.runes.api.config.ConfigFiles;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;

@Component
public final class RunesFiles implements ConfigFiles<Config, RunesFile, Inventories, Messages, Commands> {
    private @Inject Inventories inventories;
    private @Inject RunesFile runesFile;
    private @Inject Messages messages;
    private @Inject Commands commands;
    private @Inject Config config;

    @Override
    public Config config() {
        return config;
    }

    @Override
    public RunesFile trades() {
        return runesFile;
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
        runesFile.load();
        messages.load();
        inventories.load();
        commands.load();
    }
}
