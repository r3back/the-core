package com.qualityplus.crafting.api.event;

import com.qualityplus.assistant.api.event.PlayerHelperEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class AlchemistEvent extends PlayerHelperEvent {
    public AlchemistEvent(@NotNull Player who) {
        super(who);
    }
}
