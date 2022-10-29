package com.qualityplus.crafting.listener;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.crafting.api.box.Box;
import com.qualityplus.crafting.base.gui.craftingtable.CraftingTableGUI;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;

@Component
public final class VanillaTableListener implements Listener {
    private @Inject Box box;

    @EventHandler
    public void onOpenTable(PlayerInteractEvent e){
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = e.getPlayer();

        Block bl = e.getClickedBlock();

        if(!box.files().config().useAsVanillaCraftingTable) return;

        if(bl == null || bl.getType() != XMaterial.CRAFTING_TABLE.parseMaterial()) return;

        if(player.isSneaking()) return;

        e.setCancelled(true);

        e.getPlayer().openInventory(new CraftingTableGUI(box, e.getPlayer()).getInventory());
    }
}
