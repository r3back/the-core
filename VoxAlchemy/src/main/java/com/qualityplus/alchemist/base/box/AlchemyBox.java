package com.qualityplus.alchemist.base.box;

import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.alchemist.api.config.ConfigFiles;
import com.qualityplus.alchemist.base.config.Commands;
import com.qualityplus.alchemist.base.config.Config;
import com.qualityplus.alchemist.base.config.Inventories;
import com.qualityplus.alchemist.base.config.Messages;
import com.qualityplus.alchemist.base.config.RecipesFile;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

/**
 * Implementation of {@link Box}
 */
@Getter
@Component
public final class AlchemyBox implements Box {
    @Inject
    private ConfigFiles<Config, RecipesFile, Inventories, Messages, Commands> files;
    @Inject
    private Plugin plugin;
}
