package com.qualityplus.trades.api.session;

import com.qualityplus.trades.base.trades.PluginTrade;

public interface TradeSession {
    PluginTrade getTrade();
    int getAmount();
}
