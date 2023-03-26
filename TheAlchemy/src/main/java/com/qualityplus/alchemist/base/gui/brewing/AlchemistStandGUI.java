package com.qualityplus.alchemist.base.gui.brewing;

import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.alchemist.api.service.StandService;
import com.qualityplus.alchemist.base.gui.AlchemistGUI;
import com.qualityplus.assistant.util.inventory.InventoryUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public final class AlchemistStandGUI extends AlchemistGUI {
    private final AlchemistStandManager alchemistStandManager;
    private final AlchemistStandGUIConfig config;
    private final StandService standService;

    public AlchemistStandGUI(final Box box, final Location location, StandService standService) {
        super(box.files().inventories().standGUIConfig, box);

        this.standService = standService;
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
    public void onInventoryOpen(final InventoryOpenEvent e){
        Inventory inventory = e.getInventory();
        if(!inventory.equals(this.inventory)) return;
        alchemistStandManager.setItemsInInventory();
    }

    @Override
    public void onInventoryClose(final InventoryCloseEvent e){
        Inventory inventory = e.getInventory();
        if(!inventory.equals(this.inventory)) return;
        standService.removeSession(alchemistStandManager.getLocation());
    }


    @Override
    public void onInventoryDrag(final InventoryDragEvent e){
        final Inventory inventory = e.getInventory();
        if(!inventory.equals(getInventory())) return;
        final Player player = (Player) e.getWhoClicked();
        alchemistStandManager.check(player);
    }

    @Override
    public void onInventoryClick(final InventoryClickEvent e) {
        final Player player = (Player) e.getWhoClicked();
        final int slot = e.getSlot();
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
