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

/**
 * Alchemist Stand GUI
 */
public final class AlchemistStandGUI extends AlchemistGUI {
    private final AlchemistStandManager alchemistStandManager;
    private final AlchemistStandGUIConfig config;
    private final StandService standService;

    /**
     *
     * @param box          {@link Box}
     * @param location     {@link Location}
     * @param standService {@link StandService}
     */
    public AlchemistStandGUI(final Box box, final Location location, final StandService standService) {
        super(box.getFiles().inventories().getStandGUIConfig(), box);

        this.standService = standService;
        this.config = box.getFiles().inventories().getStandGUIConfig();
        this.alchemistStandManager = new AlchemistStandManager(getInventory(), location, box);
    }

    @Override
    public @NotNull Inventory getInventory() {
        InventoryUtils.fillInventory(this.inventory, this.config.getBackground());

        setItem(this.config.getCloseGUI());

        return this.inventory;
    }

    @Override
    public void onInventoryOpen(final InventoryOpenEvent e) {
        final Inventory inventory = e.getInventory();

        if (!inventory.equals(this.inventory)) {
            return;
        }

        this.alchemistStandManager.setItemsInInventory();
    }

    @Override
    public void onInventoryClose(final InventoryCloseEvent e) {
        final Inventory inventory = e.getInventory();

        if (!inventory.equals(this.inventory)) {
            return;
        }

        this.standService.removeSession(this.alchemistStandManager.getLocation());
    }


    @Override
    public void onInventoryDrag(final InventoryDragEvent e) {
        final Inventory inventory = e.getInventory();

        if (!inventory.equals(getInventory())) {
            return;
        }

        final Player player = (Player) e.getWhoClicked();

        this.alchemistStandManager.check(player);
    }

    @Override
    public void onInventoryClick(final InventoryClickEvent e) {
        final Player player = (Player) e.getWhoClicked();

        final int slot = e.getSlot();

        if (getTarget(e).equals(ClickTarget.INSIDE)) {
            if (isItem(slot, this.config.getCloseGUI())) {
                e.setCancelled(true);
                player.closeInventory();
            } else if (isClickingDecoration(slot, this.config.getBackground())) {
                e.setCancelled(true);
            }
        }

        this.alchemistStandManager.check(player);
    }
}
