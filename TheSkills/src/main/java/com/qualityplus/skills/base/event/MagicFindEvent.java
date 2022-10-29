package com.qualityplus.skills.base.event;

import com.qualityplus.skills.api.event.StatEvent;
import com.qualityplus.skills.base.stat.Stat;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class MagicFindEvent extends StatEvent {
    private @Getter @Setter ItemStack toDropItem;
    private final @Getter Location toDropLocation;

    public MagicFindEvent(@NotNull Player who, Stat stat, final ItemStack toDropItem, final Location toDropLocation) {
        super(who, stat);

        this.toDropLocation = toDropLocation;
        this.toDropItem = toDropItem;
    }
}
