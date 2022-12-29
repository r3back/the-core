package com.qualityplus.assistant.api.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

public interface FakeInventory {
    void removeItems(ItemStack itemStack, int amount);
    Map<ItemStack, Integer> getItemsWithAmount();
    void setItems(Map<Integer, ItemStack> items);
    Map<Integer, ItemStack> getItems();
    ItemStack[] getItemsArray();
    ItemStack removeOneFromLastItem();
    Inventory getInventory();
    int getEmptySlots();
    void removeItems();
    int getSlots();
    void remove();
    int getEntityId();

}
