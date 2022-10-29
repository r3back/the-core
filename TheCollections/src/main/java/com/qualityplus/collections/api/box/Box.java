package com.qualityplus.collections.api.box;

import com.qualityplus.collections.base.config.*;
import com.qualityplus.collections.persistance.CollectionsRepository;
import com.qualityplus.collections.api.config.ConfigFiles;
import com.qualityplus.collections.api.service.CollectionsService;
import eu.okaeri.injector.OkaeriInjector;
import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

public interface Box {
    ConfigFiles<Config, Inventories, Commands, Messages, CollectionsFile, CategoriesFile> files();
    CollectionsService service();
    Plugin plugin();
}
