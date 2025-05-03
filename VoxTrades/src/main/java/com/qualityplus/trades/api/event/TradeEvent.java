package com.qualityplus.trades.api.event;

import com.qualityplus.assistant.api.event.PlayerAssistantEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class TradeEvent extends PlayerAssistantEvent {
    public TradeEvent(@NotNull Player who) {
        super(who);
    }
}
