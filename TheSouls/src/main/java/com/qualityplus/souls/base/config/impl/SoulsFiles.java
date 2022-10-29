package com.qualityplus.souls.base.config.impl;

import com.qualityplus.souls.api.config.ConfigFiles;
import com.qualityplus.souls.base.config.*;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;

@Component
public final class SoulsFiles implements ConfigFiles<Config, Inventories, Messages, Commands, TiaTheFairy, Souls> {
    private @Inject Inventories inventories;
    private @Inject TiaTheFairy tiaTheFairy;
    private @Inject Messages messages;
    private @Inject Commands commands;
    private @Inject Config config;
    private @Inject Souls souls;

    @Override
    public Config config() {
        return config;
    }

    @Override
    public TiaTheFairy tiaTheFairy() {
        return tiaTheFairy;
    }

    @Override
    public Souls souls() {
        return souls;
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
        tiaTheFairy.load();
        messages.load();
        inventories.load();
        commands.load();
    }
}
