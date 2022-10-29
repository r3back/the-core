package com.qualityplus.assistant.base.event;

import com.qualityplus.assistant.api.event.PlayerHelperEvent;
import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class PlayerDamagedByEntityEvent extends PlayerHelperEvent {
    private @Getter final Entity entity;

    public PlayerDamagedByEntityEvent(@NotNull Player who, Entity entity) {
        super(who);
        this.entity = entity;
    }
}
