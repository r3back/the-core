package com.qualityplus.dragon.api.box;

import com.qualityplus.dragon.api.config.ConfigFiles;
import com.qualityplus.dragon.api.game.DragonGame;
import com.qualityplus.dragon.api.service.*;
import com.qualityplus.dragon.base.configs.*;
import org.bukkit.plugin.Plugin;

public interface Box {
    ConfigFiles<Config, DragonEventsFile, DragonGuardiansFile, DragonsFile, Inventories, Messages, DragonRewardsFile, StructuresFile, Commands> files();
    DragonService dragonService();
    StructureService structures();
    UserDBService usersDB();
    UserService users();
    DragonGame game();
    Plugin plugin();
}
