package com.qualityplus.minions.base.event;

import com.qualityplus.assistant.api.event.PlayerHelperEvent;
import com.qualityplus.minions.api.minion.MinionEntity;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * PlayerPickUpMinionItemsEvent
 */
@Getter
public final class PlayerPickUpMinionItemsEvent extends PlayerHelperEvent {
    private final MinionEntity minionEntity;
    private final List<ItemStack> items;

    /**
     *
     * @param player       Player who picked up
     * @param minionEntity MinionEntity the minion entity
     * @param items        ItemStack items picked up
     */
    public PlayerPickUpMinionItemsEvent(@NotNull final Player player, final MinionEntity minionEntity, final List<ItemStack> items) {
        super(player);
        this.minionEntity = minionEntity;
        this.items = items;
    }
}
