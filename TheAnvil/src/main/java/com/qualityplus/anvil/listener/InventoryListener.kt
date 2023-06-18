package com.qualityplus.anvil.listener

import com.qualityplus.anvil.base.gui.AnvilGUI
import eu.okaeri.platform.core.annotation.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.event.inventory.InventoryOpenEvent

@Component
class InventoryListener : Listener {
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (event.clickedInventory != null && event.inventory.holder != null) if (event.inventory.holder is AnvilGUI) (event.inventory.holder as AnvilGUI?)!!.onInventoryClick(
            event
        )
    }

    @EventHandler
    fun onInventoryClose(event: InventoryCloseEvent) {
        if (event.inventory.holder != null) if (event.inventory.holder is AnvilGUI) (event.inventory.holder as AnvilGUI?)!!.onInventoryClose(
            event
        )
    }

    @EventHandler
    fun onInventoryOpen(event: InventoryOpenEvent) {
        if (event.inventory.holder != null) if (event.inventory.holder is AnvilGUI) (event.inventory.holder as AnvilGUI?)!!.onInventoryOpen(
            event
        )
    }

    @EventHandler
    fun onInventoryOpen(event: InventoryDragEvent) {
        if (event.inventory.holder != null) if (event.inventory.holder is AnvilGUI) (event.inventory.holder as AnvilGUI?)!!.onInventoryDrag(
            event
        )
    }
}