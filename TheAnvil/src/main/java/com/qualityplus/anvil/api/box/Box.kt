package com.qualityplus.anvil.api.box

import com.qualityplus.anvil.api.config.ConfigFiles
import com.qualityplus.anvil.base.config.Commands
import com.qualityplus.anvil.base.config.Config
import com.qualityplus.anvil.base.config.Inventories
import com.qualityplus.anvil.base.config.Messages
import org.bukkit.plugin.Plugin

interface Box {
    val files: ConfigFiles<Config, Inventories, Messages, Commands>
    val plugin: Plugin
}