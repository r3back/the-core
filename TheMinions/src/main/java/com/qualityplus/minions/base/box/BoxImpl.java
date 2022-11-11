package com.qualityplus.minions.base.box;

import com.qualityplus.minions.api.service.MinionsService;
import com.qualityplus.minions.api.service.SoulsService;
import com.qualityplus.minions.api.box.Box;
import com.qualityplus.minions.api.config.ConfigFiles;
import com.qualityplus.minions.api.service.UserService;
import com.qualityplus.minions.base.config.*;
import com.qualityplus.minions.persistance.SoulsRepository;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

@Component
public final class BoxImpl implements Box {
    private @Inject ConfigFiles<Config, Inventories, Messages, Commands, TiaTheFairy, Souls> files;
    private @Inject @Getter MinionsService minionsService;
    private @Inject @Getter UserService userService;
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
