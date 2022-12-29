package com.qualityplus.collections.api.box;

import com.qualityplus.collections.api.config.ConfigFiles;
import com.qualityplus.collections.api.service.CollectionsService;
import com.qualityplus.collections.base.config.*;
import org.bukkit.plugin.Plugin;

public interface Box {
    ConfigFiles<Config, Inventories, Commands, Messages, CollectionsFile, CategoriesFile> files();
    CollectionsService service();
    Plugin plugin();
}
