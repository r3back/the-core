package com.qualityplus.assistant.api.gui;

import org.bukkit.event.inventory.InventoryCloseEvent;

public interface ClosableInventory {
    void onInventoryClose(InventoryCloseEvent event);
}
