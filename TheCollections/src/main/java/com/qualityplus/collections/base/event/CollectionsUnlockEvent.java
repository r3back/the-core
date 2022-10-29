package com.qualityplus.collections.base.event;

import com.qualityplus.collections.api.event.CollectionEvent;
import com.qualityplus.collections.base.collection.Collection;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class CollectionsUnlockEvent extends CollectionEvent {

    public CollectionsUnlockEvent(@NotNull Player who, Collection collection) {
        super(who, collection);

    }
}
