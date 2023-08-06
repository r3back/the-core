package com.qualityplus.skills.listener;

import com.qualityplus.skills.gui.SkillsGUI;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * Utility class for inventory events
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
            if (event.getInventory().getHolder() instanceof SkillsGUI) {
                ((SkillsGUI) event.getInventory().getHolder()).onInventoryClick(event);
            }
        }
    }

    /**
     * Makes a close inventory
     *
     * @param event {@link InventoryCloseEvent}
     */
    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent event) {
        if (event.getInventory().getHolder() != null) {
            if (event.getInventory().getHolder() instanceof SkillsGUI) {
                ((SkillsGUI) event.getInventory().getHolder()).onInventoryClose(event);
            }
        }
    }
}
