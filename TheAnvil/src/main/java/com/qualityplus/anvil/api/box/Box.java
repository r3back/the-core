package com.qualityplus.anvil.api.box;

import com.qualityplus.anvil.api.config.ConfigFiles;
import com.qualityplus.anvil.base.config.*;
import org.bukkit.plugin.Plugin;

public interface Box {
    ConfigFiles<Config, RecipesFile, Inventories, Messages, Commands> files();
    Plugin plugin();
}
