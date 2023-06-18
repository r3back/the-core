package com.qualityplus.anvil.base.gui.anvilmain.handler

import org.bukkit.event.inventory.InventoryClickEvent

class MainClickHandler(private val normalHandler: ClickHandler, private val shiftHandler: ClickHandler) : ClickHandler {
    override fun handle(event: InventoryClickEvent) {
        if (event.isShiftClick) {
            shiftHandler.handle(event)
        } else{
            normalHandler.handle(event)
        }
    }

    override fun handleOutSide(event: InventoryClickEvent) {
        if (event.isShiftClick) {
            shiftHandler.handleOutSide(event)
        } else {
            normalHandler.handleOutSide(event)
        }
    }
}