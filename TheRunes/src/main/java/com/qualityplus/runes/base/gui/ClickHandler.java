package com.qualityplus.runes.base.gui;

import org.bukkit.event.inventory.InventoryClickEvent;

public interface ClickHandler {
    void handle(InventoryClickEvent paramInventoryClickEvent);

    void handleOutSide(InventoryClickEvent paramInventoryClickEvent);
}
