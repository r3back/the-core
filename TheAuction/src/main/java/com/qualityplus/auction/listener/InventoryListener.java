package com.qualityplus.auction.listener;

import com.qualityplus.auction.base.gui.AuctionGUI;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

@Component
public final class InventoryListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getInventory().getHolder() != null)
            if (event.getInventory().getHolder() instanceof AuctionGUI)
                ((AuctionGUI) event.getInventory().getHolder()).onInventoryClick(event);
    }
}
