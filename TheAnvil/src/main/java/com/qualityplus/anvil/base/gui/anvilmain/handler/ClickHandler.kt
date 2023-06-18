package com.qualityplus.anvil.base.gui.anvilmain.handler

import org.bukkit.event.inventory.InventoryClickEvent

interface ClickHandler {
    fun handle(event: InventoryClickEvent)
    fun handleOutSide(event: InventoryClickEvent)
}