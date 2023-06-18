package com.qualityplus.trades.base.service;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import com.qualityplus.trades.api.provider.CheckerProvider;
import com.qualityplus.trades.api.service.TradesService;
import com.qualityplus.trades.api.session.TradeSession;
import com.qualityplus.trades.base.session.TradeSessionImpl;
import com.qualityplus.trades.base.trades.PluginTrade;
import org.bukkit.entity.Player;

@Component
public final class TradesServiceImpl implements TradesService {
    private @Inject CheckerProvider<TradeSession> provider;

    @Override
    public void checkout(Player player, TradeSession session) {
        provider.checkout(player, session);
    }

    @Override
    public TradeSession newSession(PluginTrade trade, int amount) {
        return new TradeSessionImpl(trade, amount);
    }
}
