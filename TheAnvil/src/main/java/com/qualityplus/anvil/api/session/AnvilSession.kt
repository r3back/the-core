package com.qualityplus.anvil.api.session

import org.bukkit.inventory.ItemStack

interface AnvilSession {
    var result: ItemStack?

    var itemToUpgrade: ItemStack

    var itemToSacrifice: ItemStack

    var sessionResult: SessionResult

    enum class SessionResult(val error: Boolean) {
        NOTHING_SET(false),
        BOTH_ITEMS_SET(false),
        ONLY_ITEM_TO_UPGRADE(false),
        ONLY_ITEM_TO_SACRIFICE(false),
        ERROR_CONFLICT_ENCHANTMENTS(true),
        ERROR_CANNOT_COMBINE_THOSE_ITEMS(true);
    }
}