package com.qualityplus.assistant.api.gui.fake;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class FakeGUI implements InventoryHolder {
    private final Inventory inventory;
    private final int slots;

    public FakeGUI(int slots) {
        this.inventory = Bukkit.createInventory(this, 54, "Fake");
        this.slots = slots;
    }

    public FakeGUI(ItemStack[] contents, int maxSlots){
        this.inventory = Bukkit.createInventory(this, 54, "Fake");
        this.inventory.setContents(contents);
        this.slots = maxSlots;
    }

    @Override
    public @NotNull Inventory getInventory() {
        for(int i = slots; i<inventory.getSize(); i++){
            inventory.setItem(i, XMaterial.BARRIER.parseItem());
        }

        return inventory;
    }
}
