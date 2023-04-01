package com.qualityplus.collections.base.collection.executor;


public enum ExecutorType {
    /**
     * Called when a player Pick up by {@link org.bukkit.Material}
     */
    BY_PICK_UP_MATERIAL,
    /**
     * Called when a player Pick up by {@link org.bukkit.inventory.ItemStack}
     */
    BY_PICK_UP_ITEM_STACK
}
