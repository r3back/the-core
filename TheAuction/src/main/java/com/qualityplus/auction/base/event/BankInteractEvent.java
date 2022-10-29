package com.qualityplus.auction.base.event;

import com.qualityplus.auction.api.event.BankEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class BankInteractEvent extends BankEvent {
    public BankInteractEvent(@NotNull Player who) {
        super(who);
    }
}
