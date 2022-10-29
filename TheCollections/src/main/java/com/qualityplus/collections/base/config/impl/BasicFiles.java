package com.qualityplus.collections.base.config.impl;

import com.qualityplus.collections.api.config.ConfigFiles;
import com.qualityplus.collections.base.config.*;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;

@Component
public final class BasicFiles implements ConfigFiles<Config, Inventories, Commands, Messages, CollectionsFile, CategoriesFile> {
    private @Inject CollectionsFile collectionsFile;
    private @Inject CategoriesFile categoriesFile;
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
    public CollectionsFile collections() {
        return collectionsFile;
    }

    @Override
    public CategoriesFile categories() {
        return categoriesFile;
    }

    @Override
    public void reloadFiles() {
        config.load();
        commands.load();
        messages.load();
        inventories.load();
        collectionsFile.load();
        categoriesFile.load();
    }
}
