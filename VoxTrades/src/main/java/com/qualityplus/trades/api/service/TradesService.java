package com.qualityplus.trades.api.service;

import com.qualityplus.trades.api.session.TradeSession;
import com.qualityplus.trades.base.trades.PluginTrade;
import org.bukkit.entity.Player;

public interface TradesService {
    void checkout(Player player, TradeSession session);

    TradeSession newSession(PluginTrade trade, int amount);
}
