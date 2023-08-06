package com.qualityplus.skills.base.event;

import com.qualityplus.skills.base.perk.Perk;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Utility class for item fortune perk
 */
@Getter
@Setter
public final class ItemFortunePerkEvent extends FortunePerkEvent {
    private List<ItemStack> toDropItems;

    /**
     * Makes a item fortune perk event
     *
     * @param who             {@link Player}
     * @param perk            {@link Perk}
     * @param toDropItems     List Of {@link ItemStack}
     * @param toDropLocation  {@link Location}
     */
    public ItemFortunePerkEvent(@NotNull final Player who, final Perk perk, final List<ItemStack> toDropItems, final Location toDropLocation) {
        super(who, perk, toDropLocation);

        this.toDropItems = toDropItems;
    }
}
