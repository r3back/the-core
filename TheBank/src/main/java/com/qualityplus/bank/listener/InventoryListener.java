package com.qualityplus.bank.listener;

import com.qualityplus.bank.base.gui.BankGUI;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

@Component
public final class InventoryListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getInventory().getHolder() != null)
            if (event.getInventory().getHolder() instanceof BankGUI)
                ((BankGUI) event.getInventory().getHolder()).onInventoryClick(event);
    }
}
