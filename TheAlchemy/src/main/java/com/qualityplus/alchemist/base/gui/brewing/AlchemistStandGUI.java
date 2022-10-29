package com.qualityplus.alchemist.base.gui.brewing;

import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.alchemist.base.gui.AlchemistGUI;
import com.qualityplus.assistant.util.inventory.InventoryUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public final class AlchemistStandGUI extends AlchemistGUI {
    private final AlchemistStandManager alchemistStandManager;
    private final AlchemistStandGUIConfig config;

    public AlchemistStandGUI(Box box, Location location) {
        super(box.files().inventories().standGUIConfig, box);

        this.config = box.files().inventories().standGUIConfig;
        this.alchemistStandManager = new AlchemistStandManager(getInventory(), location, box);
    }

    @Override
    public @NotNull Inventory getInventory() {
        InventoryUtils.fillInventory(inventory, config.getBackground());

        setItem(config.getCloseGUI());

        return inventory;
    }

    @Override
    public void onInventoryOpen(InventoryOpenEvent e){
        Inventory inventory = e.getInventory();
        if(!inventory.equals(this.inventory)) return;
        alchemistStandManager.setItemsInInventory();
    }

    @Override
    public void onInventoryDrag(InventoryDragEvent e){
        Inventory inventory = e.getInventory();
        if(!inventory.equals(getInventory())) return;
        Player player = (Player) e.getWhoClicked();
        alchemistStandManager.check(player);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        int slot = e.getSlot();
        if(getTarget(e).equals(ClickTarget.INSIDE)){
            if (isItem(slot, config.getCloseGUI())) {
                e.setCancelled(true);
                player.closeInventory();
            }else if (isClickingDecoration(slot, config.getBackground())) {
                e.setCancelled(true);
            }
        }
        alchemistStandManager.check(player);
    }
}
