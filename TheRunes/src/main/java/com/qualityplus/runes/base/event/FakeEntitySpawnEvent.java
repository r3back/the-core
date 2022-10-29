package com.qualityplus.runes.base.event;

import com.qualityplus.assistant.api.event.HelperEvent;
import lombok.Getter;
import org.bukkit.entity.Entity;

public final class FakeEntitySpawnEvent extends HelperEvent {
    private @Getter final Entity entity;

    public FakeEntitySpawnEvent(Entity entity) {
        this.entity = entity;
    }
}
