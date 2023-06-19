package com.qualityplus.collections.api.event;

import com.qualityplus.assistant.api.event.PlayerAssistantEvent;
import com.qualityplus.collections.base.collection.Collection;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public abstract class CollectionEvent extends PlayerAssistantEvent {
    private Collection collection;

    public CollectionEvent(@NotNull Player who, Collection collection) {
        super(who);
        this.collection = collection;
    }
}
