package com.qualityplus.assistant.util.inventory;

import com.qualityplus.assistant.inventory.background.Background;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InventoryUtils {
    public static void fillInventory(Inventory inventory, Background background) {
        if(background.useFiller && background.filler != null){
            for (int i = 0; i < inventory.getSize(); i++)
                inventory.setItem(i, ItemStackUtils.makeItem(background.filler));
        }

        if (background.items == null) return;

        for (int slot : background.items.keySet()) {
            if (slot >= inventory.getSize()) continue;
            inventory.setItem(slot, ItemStackUtils.makeItem(background.items.get(slot)));
        }
    }

    public static int getItemQuantity(ItemStack[] itemStacks, ItemStack itemStackParam) {
        int quantity = 0;

        for (ItemStack itemStack : itemStacks) {
            if (itemStack == null) continue;

            ItemStack tempItemStack = itemStack.clone();

            tempItemStack.setAmount(itemStack.getMaxStackSize());

            if (!tempItemStack.isSimilar(itemStackParam)) continue;

            quantity += itemStack.getAmount();
        }

        return quantity;
    }

    public static void removeItems(Inventory inventory, ItemStack stack, int amount){
        for(int i = 0; i<64; i++){
            if(amount <= 0 || !inventory.containsAtLeast(stack, amount)) break;

            ItemStack toRemove = stack.clone();

            toRemove.setAmount(amount);

            amount = inventory.removeItem(toRemove).keySet().stream().findFirst()
                    .orElse(0);

        }
    }

    public static void addItems(Inventory inventory, ItemStack stack, int amount){
        if(ItemStackUtils.isNull(stack)) return;

        ItemStack toAdd = stack.clone();

        toAdd.setAmount(1);

        for(int i = 0; i<amount; i++)
            inventory.addItem(toAdd.clone());

    }

    public static void removeItems(Inventory inventory, ItemStack stack){
        int amount = stack.getAmount();

        ItemStack modified = ItemStackUtils.getItemWithout(stack, amount);

        removeItems(inventory, modified, amount);
    }
}
