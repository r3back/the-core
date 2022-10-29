package com.qualityplus.enchanting.base.gui.enchantmain.handler;

import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.enchanting.api.box.Box;
import com.qualityplus.enchanting.base.gui.enchantmain.EnchantMainGUI;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

@AllArgsConstructor
public final class ShiftClickHandler implements ClickHandler{
    private final EnchantMainGUI gui;
    private final ItemStack item;
    private final int bookShelf;
    private final int page;
    private final Box box;

    @Override
    public void handle(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        final ItemStack copy = Optional.ofNullable(event.getCursor()).map(ItemStack::clone).orElse(null);
        final ItemStack current = Optional.ofNullable(event.getCurrentItem()).map(ItemStack::clone).orElse(null);

        if(ItemStackUtils.isNull(copy) && !ItemStackUtils.isNull(current)){
            //event.setCurrentItem(null);
            gui.setGiveItem(false);
            player.openInventory(new EnchantMainGUI(box, page, bookShelf, null).getInventory());
            player.getInventory().addItem(current);
        }
    }

    @Override
    public void handleOutSide(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        final ItemStack current = Optional.ofNullable(event.getCurrentItem()).map(ItemStack::clone).orElse(null);

        if(ItemStackUtils.isNull(item) && !ItemStackUtils.isNull(current)){
            gui.setGiveItem(false);

            event.setCurrentItem(null);

            player.openInventory(new EnchantMainGUI(box, page, bookShelf, current).getInventory());
        }
    }
}
