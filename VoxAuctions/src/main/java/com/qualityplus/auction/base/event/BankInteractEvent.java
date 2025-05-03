package com.qualityplus.auction.base.event;

import com.qualityplus.auction.api.event.BankEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class bank interact event
 */
public final class BankInteractEvent extends BankEvent {
    /**
     * Adds bank interact event
     *
     * @param who {@link BankInteractEvent}
     */
    public BankInteractEvent(@NotNull final Player who) {
        super(who);
    }
}
