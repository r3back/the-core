package com.qualityplus.souls.base.box;

import com.qualityplus.souls.api.box.Box;
import com.qualityplus.souls.api.config.ConfigFiles;
import com.qualityplus.souls.api.service.SoulsService;
import com.qualityplus.souls.base.config.*;
import com.qualityplus.souls.persistance.SoulsRepository;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

@Component
public final class BoxImpl implements Box {
    private @Inject ConfigFiles<Config, Inventories, Messages, Commands, TiaTheFairy, Souls> files;
    private @Inject SoulsRepository repository;
    private @Inject SoulsService service;
    private @Inject Plugin plugin;

    @Override
    public ConfigFiles<Config, Inventories, Messages, Commands, TiaTheFairy, Souls> files() {
        return files;
    }

    @Override
    public SoulsRepository repository() {
        return repository;
    }

    @Override
    public SoulsService service() {
        return service;
    }

    @Override
    public Plugin plugin() {
        return plugin;
    }
}
