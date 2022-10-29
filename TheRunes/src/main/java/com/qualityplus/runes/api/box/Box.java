package com.qualityplus.runes.api.box;

import com.qualityplus.runes.api.service.RunesService;
import com.qualityplus.runes.base.config.*;
import com.qualityplus.runes.api.config.ConfigFiles;
import org.bukkit.plugin.Plugin;

public interface Box {
    ConfigFiles<Config, RunesFile, Inventories, Messages, Commands> files();

    RunesService runesService();

    Plugin plugin();
}
