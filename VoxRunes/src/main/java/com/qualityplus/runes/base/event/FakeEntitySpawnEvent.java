package com.qualityplus.runes.base.event;

import com.qualityplus.assistant.api.event.AssistantEvent;
import lombok.Getter;
import org.bukkit.entity.Entity;

public final class FakeEntitySpawnEvent extends AssistantEvent {
    private @Getter final Entity entity;

    public FakeEntitySpawnEvent(Entity entity) {
        this.entity = entity;
    }
}
