package com.qualityplus.collections.base.event;

import com.qualityplus.collections.api.event.CollectionEvent;
import com.qualityplus.collections.base.collection.Collection;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class CollectionsXPGainEvent extends CollectionEvent {
    private @Getter @Setter double xp;

    public CollectionsXPGainEvent(@NotNull Player who, Collection collection, double xp) {
        super(who, collection);
        this.xp = xp;
    }
}
