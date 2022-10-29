package com.qualityplus.enchanting.api.box;

import com.qualityplus.enchanting.api.config.ConfigFiles;
import com.qualityplus.enchanting.base.config.*;
import org.bukkit.plugin.Plugin;

public interface Box {
    ConfigFiles<Config, Inventories, Messages, Commands> files();
    Plugin plugin();
}
