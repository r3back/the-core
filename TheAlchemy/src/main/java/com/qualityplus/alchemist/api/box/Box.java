package com.qualityplus.alchemist.api.box;

import com.qualityplus.alchemist.api.config.ConfigFiles;
import com.qualityplus.alchemist.base.config.*;
import org.bukkit.plugin.Plugin;

public interface Box {
    ConfigFiles<Config, RecipesFile, Inventories, Messages, Commands> files();
    Plugin plugin();
}
