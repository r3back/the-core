package com.qualityplus.enchanting.api.event;

import com.qualityplus.assistant.api.event.PlayerHelperEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class EnchantingEvent extends PlayerHelperEvent {
    public EnchantingEvent(@NotNull Player who) {
        super(who);
    }
}
