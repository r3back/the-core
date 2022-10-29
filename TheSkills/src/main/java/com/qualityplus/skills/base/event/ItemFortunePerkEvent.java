package com.qualityplus.skills.base.event;

import com.qualityplus.skills.base.perk.Perk;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@Setter
public final class ItemFortunePerkEvent extends FortunePerkEvent {
    private List<ItemStack> toDropItems;

    public ItemFortunePerkEvent(@NotNull Player who, Perk perk, List<ItemStack> toDropItems, Location toDropLocation) {
        super(who, perk, toDropLocation);

        this.toDropItems = toDropItems;
    }
}
