package com.qualityplus.minions.base.gui.main.setup;

import com.qualityplus.minions.api.box.Box;
import com.qualityplus.minions.api.minion.MinionEntity;
import com.qualityplus.minions.base.gui.main.MainMinionGUIConfig;
import org.bukkit.inventory.Inventory;

public interface ItemSetup {
    void setItem(Inventory inventory, Box box, MainMinionGUIConfig config, MinionEntity minionEntity);
}
