package com.qualityplus.trades.base.event;

import com.qualityplus.trades.api.event.TradeEvent;
import com.qualityplus.trades.base.trades.PluginTrade;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import lombok.Getter;

@Getter
public final class PlayerTradeEvent extends TradeEvent {
    private final PluginTrade trade;

    public PlayerTradeEvent(@NotNull Player who, PluginTrade trade) {
        super(who);

        this.trade = trade;
    }
}
