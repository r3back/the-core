package com.qualityplus.pets.api.box;

import com.qualityplus.pets.api.config.ConfigFiles;
import com.qualityplus.pets.api.service.PetsService;
import com.qualityplus.pets.base.config.*;
import org.bukkit.plugin.Plugin;

public interface Box {
    ConfigFiles<Config, Inventories, Messages, Commands> files();
    PetsService service();
    Plugin plugin();
}
