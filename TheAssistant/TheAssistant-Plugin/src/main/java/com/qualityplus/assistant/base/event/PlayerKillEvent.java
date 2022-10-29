package com.qualityplus.assistant.base.event;

import com.qualityplus.assistant.api.event.PlayerHelperEvent;
import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class PlayerKillEvent extends PlayerHelperEvent {
    private @Getter final Entity killed;

    public PlayerKillEvent(@NotNull Player who, Entity killed) {
        super(who);
        this.killed = killed;
    }
}
