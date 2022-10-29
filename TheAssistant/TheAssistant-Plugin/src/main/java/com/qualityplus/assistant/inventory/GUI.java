package com.qualityplus.assistant.inventory;

import com.qualityplus.assistant.api.gui.LoreWrapper;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.api.gui.ClickableInventory;
import com.qualityplus.assistant.inventory.background.Background;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.inventory.InventoryUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public abstract class GUI implements InventoryHolder, ClickableInventory<Item, Background> {
    protected Inventory inventory;
    protected int page = 1;
    protected boolean hasNext;
    protected int maxPerPage;

    public GUI(int size, String title) {
        this.inventory = Bukkit.createInventory(this, size, StringUtils.color(title));
    }

    public GUI(SimpleGUI simpleGUI) {
        this.inventory = Bukkit.createInventory(this, simpleGUI.getSize(), StringUtils.color(simpleGUI.getTitle()));
    }

    protected void fillInventory(SimpleGUI simpleGUI){
        InventoryUtils.fillInventory(inventory, simpleGUI.getBackground());
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    @Override
    public boolean isClickingDecoration(Integer clickedSlot, Background background){
        return Optional.ofNullable(background.items).map(back -> back.keySet().stream().anyMatch(slot -> slot.equals(clickedSlot))).orElse(false);
    }

    @Override
    public void setItem(Item item) {
        if(!item.enabled || item.slot == null) return;

        inventory.setItem(item.slot, ItemStackUtils.makeItem(item));
    }

    @Override
    public void setItem(Item item, List<IPlaceholder> placeholderList) {
        if(!item.enabled || item.slot == null) return;

        inventory.setItem(item.slot, ItemStackUtils.makeItem(item, placeholderList));
    }

    @Override
    public void setItem(Item item, LoreWrapper loreWrapper) {
        if(!item.enabled || item.slot == null) return;

        inventory.setItem(item.slot, ItemStackUtils.makeItem(item, loreWrapper));
    }

    @Override
    public void setItem(Item item, List<IPlaceholder> placeholderList, LoreWrapper loreWrapper) {
        if(!item.enabled || item.slot == null) return;

        inventory.setItem(item.slot, ItemStackUtils.makeItem(item, placeholderList, loreWrapper));
    }

    protected boolean isItem(int slot, Item item){
        return item.enabled && item.slot == slot;
    }

    public void onInventoryClose(InventoryCloseEvent event){}

    public void onInventoryOpen(InventoryOpenEvent event){}

    public void onInventoryDrag(InventoryDragEvent event){}

    public static ClickTarget getTarget(InventoryClickEvent e){
        return e.getClickedInventory() == null ? ClickTarget.NONE : e.getClickedInventory().equals(e.getInventory()) ? ClickTarget.INSIDE : ClickTarget.PLAYER;
    }

    protected enum ClickTarget{
        NONE,
        /**
         * CALLED WHEN PLAYER CLICKS INSIDE PLUGIN GUI
         */
        INSIDE,
        /**
         * CALLED WHEN PLAYER CLICKS IN IT'S OWN INVENTORY
         */
        PLAYER
    }
}
