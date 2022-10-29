package com.qualityplus.crafting.api.box;

import com.qualityplus.crafting.api.config.ConfigFiles;
import com.qualityplus.crafting.base.config.*;
import org.bukkit.plugin.Plugin;

public interface Box {
    ConfigFiles<Config, RecipesFile, Inventories, Messages, Commands, Categories> files();
    Plugin plugin();
}
