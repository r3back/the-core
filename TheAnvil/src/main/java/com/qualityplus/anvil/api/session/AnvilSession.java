package com.qualityplus.anvil.api.session;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

public interface AnvilSession {
    ItemStack getResult();
    ItemStack getItemToUpgrade();
    ItemStack getItemToSacrifice();
    SessionResult getSessionResult();

    @AllArgsConstructor
    public enum SessionResult{
        ONLY_ITEM_TO_UPGRADE(false),
        ONLY_ITEM_TO_SACRIFICE(false),
        BOTH_ITEMS_SET(false),
        NOTHING_SET(false),
        ERROR_CANNOT_COMBINE_THOSE_ITEMS(true),
        ERROR_CONFLICT_ENCHANTMENTS(true);

        private final @Getter
        boolean error;
    }
}
