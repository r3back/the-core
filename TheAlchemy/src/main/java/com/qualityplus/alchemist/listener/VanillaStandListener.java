package com.qualityplus.alchemist.listener;

import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.alchemist.base.gui.brewing.AlchemistStandGUI;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

@Component
public final class VanillaStandListener implements Listener {
    private @Inject Box box;

    @EventHandler
    public void onOpenBrewingStand(PlayerInteractEvent e){
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Block bl = e.getClickedBlock();
        if(bl == null || bl.getType() != Material.BREWING_STAND) return;

        if(!box.files().config().openAsVanillaBrewingStand) return;

        e.setCancelled(true);
        Location location = bl.getLocation();
        e.getPlayer().openInventory(new AlchemistStandGUI(box, location).getInventory());
    }
}
