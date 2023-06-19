package com.qualityplus.alchemist.base.config.impl;

import com.qualityplus.alchemist.api.config.ConfigFiles;
import com.qualityplus.alchemist.base.config.Commands;
import com.qualityplus.alchemist.base.config.Config;
import com.qualityplus.alchemist.base.config.Inventories;
import com.qualityplus.alchemist.base.config.Messages;
import com.qualityplus.alchemist.base.config.RecipesFile;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;

/**
 * Implementation of {@link ConfigFiles}
 */
@Component
public final class AlchemyFiles implements ConfigFiles<Config, RecipesFile, Inventories, Messages, Commands> {
    private @Inject Inventories inventories;
    private @Inject RecipesFile recipes;
    private @Inject Messages messages;
    private @Inject Commands commands;
    private @Inject Config config;

    @Override
    public Config config() {
        return this.config;
    }

    @Override
    public RecipesFile recipes() {
        return this.recipes;
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
        this.recipes.load();
        this.messages.load();
        this.inventories.load();
        this.commands.load();
    }
}
