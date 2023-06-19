package com.qualityplus.anvil.base.box;

import com.qualityplus.anvil.api.box.Box;
import com.qualityplus.anvil.api.config.ConfigFiles;
import com.qualityplus.anvil.base.config.Commands;
import com.qualityplus.anvil.base.config.Config;
import com.qualityplus.anvil.base.config.Inventories;
import com.qualityplus.anvil.base.config.Messages;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

@Component
public final class AnvilBox implements Box {
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
