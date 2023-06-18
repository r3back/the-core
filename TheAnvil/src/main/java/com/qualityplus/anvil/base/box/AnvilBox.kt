package com.qualityplus.anvil.base.box

import com.qualityplus.anvil.api.box.Box
import com.qualityplus.anvil.api.config.ConfigFiles
import com.qualityplus.anvil.base.config.Commands
import com.qualityplus.anvil.base.config.Config
import com.qualityplus.anvil.base.config.Inventories
import com.qualityplus.anvil.base.config.Messages
import eu.okaeri.injector.annotation.Inject
import eu.okaeri.platform.core.annotation.Component
import org.bukkit.plugin.Plugin

@Component
class AnvilBox : Box {
    @Inject
    override lateinit var files: ConfigFiles<Config, Inventories, Messages, Commands>

    @Inject
    override lateinit var plugin: Plugin
}