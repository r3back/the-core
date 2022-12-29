package com.qualityplus.assistant.api.util;

import com.qualityplus.assistant.api.gui.fake.FakeGUI;
import lombok.experimental.UtilityClass;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class FakeInventoryFactory {
    public Inventory getInventoryWithSize(int maxSlots){
        return new FakeGUI(maxSlots).getInventory();
    }

    public Inventory getInventoryWithItems(ItemStack[] contents){
        return new FakeGUI(contents, 0).getInventory();
    }

    public Inventory getInventoryWithSize(ItemStack[] contents, int maxSlots){
        return new FakeGUI(contents, maxSlots).getInventory();
    }
}
