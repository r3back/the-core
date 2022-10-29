package com.qualityplus.dragon.base.box;

import com.qualityplus.dragon.api.box.Box;
import com.qualityplus.dragon.api.config.ConfigFiles;
import com.qualityplus.dragon.api.game.DragonGame;
import com.qualityplus.dragon.api.service.*;
import com.qualityplus.dragon.base.configs.*;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

@Component
public final class BoxImpl implements Box {
    private @Inject ConfigFiles<Config, DragonEventsFile, DragonGuardiansFile, DragonsFile, Inventories, Messages, DragonRewardsFile, StructuresFile, Commands> files;
    private @Inject StructureService structureService;
    private @Inject DragonService dragonService;
    private @Inject UserDBService userDBService;
    private @Inject UserService userService;
    private @Inject DragonGame dragonGame;
    private @Inject Plugin plugin;

    @Override
    public ConfigFiles<Config, DragonEventsFile, DragonGuardiansFile, DragonsFile, Inventories, Messages, DragonRewardsFile, StructuresFile, Commands> files() {
        return files;
    }

    @Override
    public DragonService dragonService() {
        return dragonService;
    }

    @Override
    public StructureService structures() {
        return structureService;
    }

    @Override
    public UserDBService usersDB() {
        return userDBService;
    }

    @Override
    public UserService users() {
        return userService;
    }

    @Override
    public DragonGame game() {
        return dragonGame;
    }

    @Override
    public Plugin plugin() {
        return plugin;
    }
}
