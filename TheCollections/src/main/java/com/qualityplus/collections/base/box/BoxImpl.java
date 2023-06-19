package com.qualityplus.collections.base.box;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.collections.api.box.Box;
import com.qualityplus.collections.api.config.ConfigFiles;
import com.qualityplus.collections.api.service.CollectionsService;
import com.qualityplus.collections.base.config.*;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

@Component
public final class BoxImpl implements Box {
    private @Inject ConfigFiles<Config, Inventories, Commands, Messages, CollectionsFile, CategoriesFile> files;
    private @Inject CollectionsService service;
    private @Inject Plugin plugin;

    @Override
    public ConfigFiles<Config, Inventories, Commands, Messages, CollectionsFile, CategoriesFile> files() {
        return files;
    }

    @Override
    public CollectionsService service() {
        return service;
    }

    @Override
    public Plugin plugin() {
        return plugin;
    }
}
