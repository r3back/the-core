package com.qualityplus.runes.api.event;

import com.qualityplus.assistant.api.event.PlayerAssistantEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class PlayerRuneEvent extends PlayerAssistantEvent {
    public PlayerRuneEvent(@NotNull Player who) {
        super(who);
    }
}
