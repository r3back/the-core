package com.qualityplus.runes.api.event;

import com.qualityplus.assistant.api.event.PlayerHelperEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class PlayerRuneEvent extends PlayerHelperEvent {
    public PlayerRuneEvent(@NotNull Player who) {
        super(who);
    }
}
