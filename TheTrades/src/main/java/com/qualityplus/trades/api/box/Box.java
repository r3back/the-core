package com.qualityplus.trades.api.box;

import com.qualityplus.trades.api.config.ConfigFiles;
import com.qualityplus.trades.api.service.TradesService;
import com.qualityplus.trades.base.config.*;
import org.bukkit.plugin.Plugin;

public interface Box {
    ConfigFiles<Config, TradesFile, Inventories, Messages, Commands> files();

    TradesService tradesService();

    Plugin plugin();
}
