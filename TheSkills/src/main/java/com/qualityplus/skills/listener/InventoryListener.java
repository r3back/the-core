package com.qualityplus.skills.listener;

import com.qualityplus.skills.gui.SkillsGUI;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

@Component
public final class InventoryListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getInventory().getHolder() != null)
            if (event.getInventory().getHolder() instanceof SkillsGUI)
                ((SkillsGUI) event.getInventory().getHolder()).onInventoryClick(event);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() != null)
            if (event.getInventory().getHolder() instanceof SkillsGUI)
                ((SkillsGUI) event.getInventory().getHolder()).onInventoryClose(event);
    }
}
