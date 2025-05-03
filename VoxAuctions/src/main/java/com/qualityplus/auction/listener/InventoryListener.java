package com.qualityplus.auction.listener;

import com.qualityplus.auction.base.gui.AuctionGUI;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Utility class for inventory
 */
@Component
public final class InventoryListener implements Listener {
    /**
     * Makes an inventory click
     *
     * @param event {@link InventoryClickEvent}
     */
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getInventory().getHolder() != null) {
            if (event.getInventory().getHolder() instanceof AuctionGUI) {
                ((AuctionGUI) event.getInventory().getHolder()).onInventoryClick(event);
            }
        }
    }
}
