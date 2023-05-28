package com.qualityplus.alchemist.listener;

import com.qualityplus.alchemist.base.gui.AlchemistGUI;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

/**
 * Inventory Listener
 */
@Component
public final class InventoryListener implements Listener {
    /**
     *
     * @param event {@link InventoryClickEvent}
     */
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getInventory().getHolder() != null) {
            if (event.getInventory().getHolder() instanceof AlchemistGUI) {
                ((AlchemistGUI) event.getInventory().getHolder()).onInventoryClick(event);
            }
        }
    }

    /**
     *
     * @param event {@link InventoryCloseEvent}
     */
    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent event) {
        if (event.getInventory().getHolder() != null) {
            if (event.getInventory().getHolder() instanceof AlchemistGUI) {
                ((AlchemistGUI) event.getInventory().getHolder()).onInventoryClose(event);
            }
        }
    }

    /**
     *
     * @param event {@link InventoryOpenEvent}
     */
    @EventHandler
    public void onInventoryOpen(final InventoryOpenEvent event) {
        if (event.getInventory().getHolder() != null) {
            if (event.getInventory().getHolder() instanceof AlchemistGUI) {
                ((AlchemistGUI) event.getInventory().getHolder()).onInventoryOpen(event);
            }
        }
    }

    /**
     *
     * @param event {@link InventoryDragEvent}
     */
    @EventHandler
    public void onInventoryOpen(final InventoryDragEvent event) {
        if (event.getInventory().getHolder() != null) {
            if (event.getInventory().getHolder() instanceof AlchemistGUI) {
                ((AlchemistGUI) event.getInventory().getHolder()).onInventoryDrag(event);
            }
        }
    }
}
