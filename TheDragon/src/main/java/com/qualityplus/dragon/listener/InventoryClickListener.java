package com.qualityplus.dragon.listener;

import com.qualityplus.dragon.gui.TheDragonGUI;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

@Component
public final class InventoryClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getInventory().getHolder() != null && event.getInventory().getHolder() instanceof TheDragonGUI) {
            event.setCancelled(true);
            ((TheDragonGUI) event.getInventory().getHolder()).onInventoryClick(event);
        }
    }
}
