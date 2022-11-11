package com.qualityplus.minions.api.box;

import com.qualityplus.minions.api.service.MinionsService;
import com.qualityplus.minions.api.service.SoulsService;
import com.qualityplus.minions.api.config.ConfigFiles;
import com.qualityplus.minions.api.service.UserService;
import com.qualityplus.minions.base.config.*;
import com.qualityplus.minions.persistance.SoulsRepository;
import org.bukkit.plugin.Plugin;

public interface Box {
    ConfigFiles<Config, Inventories, Messages, Commands, TiaTheFairy, Souls> files();
    SoulsRepository repository();
    SoulsService service();
    MinionsService getMinionsService();
    UserService getUserService();
    Plugin plugin();
}
