package com.qualityplus.skills.base.event;

import com.qualityplus.skills.api.event.StatEvent;
import com.qualityplus.skills.base.stat.Stat;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for magic find
 */
public final class MagicFindEvent extends StatEvent {
    private @Getter @Setter ItemStack toDropItem;
    private final @Getter Location toDropLocation;

    /**
     * Makes a magic find
     *
     * @param who            Who
     * @param stat           {@link Stat}
     * @param toDropItem     {@link ItemStack}
     * @param toDropLocation {@link Location}
     */
    public MagicFindEvent(@NotNull final Player who, final Stat stat, final ItemStack toDropItem, final Location toDropLocation) {
        super(who, stat);

        this.toDropLocation = toDropLocation;
        this.toDropItem = toDropItem;
    }
}
