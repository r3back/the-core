package com.qualityplus.enchanting.base.box;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.enchanting.api.box.Box;
import com.qualityplus.enchanting.api.config.ConfigFiles;
import com.qualityplus.enchanting.base.config.Commands;
import com.qualityplus.enchanting.base.config.Config;
import com.qualityplus.enchanting.base.config.Inventories;
import com.qualityplus.enchanting.base.config.Messages;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

@Component
public final class EnchantingBox implements Box {
    private @Inject ConfigFiles<Config, Inventories, Messages, Commands> files;
    private @Inject Plugin plugin;

    @Override
    public ConfigFiles<Config, Inventories, Messages, Commands> files() {
        return files;
    }

    @Override
    public Plugin plugin() {
        return plugin;
    }
}
