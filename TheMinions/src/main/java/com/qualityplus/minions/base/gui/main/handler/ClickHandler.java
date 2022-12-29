package com.qualityplus.minions.base.gui.main.handler;

import com.qualityplus.minions.api.box.Box;
import com.qualityplus.minions.api.minion.MinionEntity;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface ClickHandler {
    void handle(InventoryClickEvent event, MinionEntity minionEntity, Box box);

}
