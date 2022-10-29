package com.qualityplus.trades.base.session;

import com.qualityplus.trades.api.session.TradeSession;
import com.qualityplus.trades.base.trades.PluginTrade;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class TradeSessionImpl implements TradeSession {
    private final PluginTrade trade;
    private final int amount;
}
