package com.qualityplus.enchanting.base.gui.enchantmain.handler;

import org.bukkit.event.inventory.InventoryClickEvent;

public interface ClickHandler {
    void handle(InventoryClickEvent event);

    void handleOutSide(InventoryClickEvent event);
}
