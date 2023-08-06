package com.qualityplus.auction.api.event;

import com.qualityplus.assistant.api.event.PlayerAssistantEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for bank event
 */
public abstract class BankEvent extends PlayerAssistantEvent {

    /**
     * Adds bank event
     *
     * @param who {@link Player}
     */
    public BankEvent(final @NotNull Player who) {
        super(who);
    }
}
