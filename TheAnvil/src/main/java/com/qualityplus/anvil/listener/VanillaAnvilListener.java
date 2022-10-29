package com.qualityplus.anvil.listener;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.anvil.base.gui.anvilmain.AnvilMainGUI;
import com.qualityplus.anvil.api.box.Box;
import com.qualityplus.anvil.base.session.AnvilSessionImpl;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public final class VanillaAnvilListener implements Listener {
    private @Inject Box box;

    @EventHandler
    public void onOpenBrewingStand(PlayerInteractEvent e){
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Block block = e.getClickedBlock();

        if(block == null) return;

        if(!blockIsAnvil(block)) return;

        if(!box.files().config().openAsVanillaAnvil) return;

        e.setCancelled(true);
        e.getPlayer().openInventory(new AnvilMainGUI(box, new AnvilSessionImpl(null, null, null)).getInventory());
    }

    private boolean blockIsAnvil(Block block){
        Material material = block.getType();

        return Stream.of(XMaterial.ANVIL, XMaterial.CHIPPED_ANVIL, XMaterial.DAMAGED_ANVIL)
                .map(XMaterial::parseMaterial)
                .collect(Collectors.toList())
                .contains(material);
    }
}