package com.qualityplus.crafting.base.config.impl;

import com.qualityplus.crafting.api.config.ConfigFiles;
import com.qualityplus.crafting.base.config.*;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;

@Component
public final class AlchemyFiles implements ConfigFiles<Config, RecipesFile, Inventories, Messages, Commands, Categories> {
    private @Inject Inventories inventories;
    private @Inject Categories categories;
    private @Inject RecipesFile recipes;
    private @Inject Messages messages;
    private @Inject Commands commands;
    private @Inject Config config;

    @Override
    public Config config() {
        return config;
    }

    @Override
    public RecipesFile recipes() {
        return recipes;
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
    public Categories categories() {
        return categories;
    }

    @Override
    public void reloadFiles() {
        config.load();
        //recipes.load();
        messages.load();
        inventories.load();
        commands.load();
        categories.load();
    }
}
