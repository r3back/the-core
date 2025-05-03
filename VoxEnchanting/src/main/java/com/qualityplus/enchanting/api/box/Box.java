package com.qualityplus.enchanting.api.box;

import com.qualityplus.enchanting.api.config.ConfigFiles;
import com.qualityplus.enchanting.base.config.Commands;
import com.qualityplus.enchanting.base.config.Config;
import com.qualityplus.enchanting.base.config.Inventories;
import com.qualityplus.enchanting.base.config.Messages;
import org.bukkit.plugin.Plugin;

public interface Box {
    ConfigFiles<Config, Inventories, Messages, Commands> files();
    Plugin plugin();
}
