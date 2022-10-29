package com.qualityplus.collections.base.event;

import com.qualityplus.collections.api.event.CollectionEvent;
import com.qualityplus.collections.base.collection.Collection;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class CollectionsLevelUPEvent extends CollectionEvent {
    private @Getter final int newLevel;

    public CollectionsLevelUPEvent(@NotNull Player who, Collection collection, int newLevel) {
        super(who, collection);

        this.newLevel = newLevel;
    }
}
