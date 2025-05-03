package com.qualityplus.alchemist.api.box;

import com.qualityplus.alchemist.api.config.ConfigFiles;
import com.qualityplus.alchemist.base.config.Commands;
import com.qualityplus.alchemist.base.config.Config;
import com.qualityplus.alchemist.base.config.Inventories;
import com.qualityplus.alchemist.base.config.Messages;
import com.qualityplus.alchemist.base.config.RecipesFile;
import org.bukkit.plugin.Plugin;

/**
 * Interface to inject dependencies
 */
public interface Box {
    /**
     * Return config files container
     *
     * @return {@link ConfigFiles}
     */
    public ConfigFiles<Config, RecipesFile, Inventories, Messages, Commands> getFiles();

    /**
     * Retrieves plugin instance
     *
     * @return {@link Plugin}
     */
    public Plugin getPlugin();
}
