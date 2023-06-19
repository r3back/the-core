package com.qualityplus.auction.api.event;

import com.qualityplus.assistant.api.event.PlayerAssistantEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class BankEvent extends PlayerAssistantEvent {

    public BankEvent(@NotNull Player who) {
        super(who);
    }
}
