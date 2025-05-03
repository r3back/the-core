package com.qualityplus.souls.listener;

import com.qualityplus.souls.base.gui.SoulsGUI;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

@Component
public final class InventoryListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getInventory().getHolder() != null)
            if (event.getInventory().getHolder() instanceof SoulsGUI)
                ((SoulsGUI) event.getInventory().getHolder()).onInventoryClick(event);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() != null)
            if (event.getInventory().getHolder() instanceof SoulsGUI)
                ((SoulsGUI) event.getInventory().getHolder()).onInventoryClose(event);
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (event.getInventory().getHolder() != null)
            if (event.getInventory().getHolder() instanceof SoulsGUI)
                ((SoulsGUI) event.getInventory().getHolder()).onInventoryOpen(event);
    }

    @EventHandler
    public void onInventoryOpen(InventoryDragEvent event) {
        if (event.getInventory().getHolder() != null)
            if (event.getInventory().getHolder() instanceof SoulsGUI)
                ((SoulsGUI) event.getInventory().getHolder()).onInventoryDrag(event);
    }
}
