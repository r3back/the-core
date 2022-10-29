package com.qualityplus.anvil.base.gui.anvilmain.handler;

import org.bukkit.event.inventory.InventoryClickEvent;

public interface ClickHandler {
    void handle(InventoryClickEvent event);

    void handleOutSide(InventoryClickEvent event);
}
