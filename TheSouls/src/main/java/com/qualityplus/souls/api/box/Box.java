package com.qualityplus.souls.api.box;

import com.qualityplus.souls.api.config.ConfigFiles;
import com.qualityplus.souls.api.service.SoulsService;
import com.qualityplus.souls.base.config.*;
import com.qualityplus.souls.persistance.SoulsRepository;
import org.bukkit.plugin.Plugin;

public interface Box {
    ConfigFiles<Config, Inventories, Messages, Commands, TiaTheFairy, Souls> files();
    SoulsRepository repository();
    SoulsService service();
    Plugin plugin();
}
