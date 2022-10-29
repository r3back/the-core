package com.qualityplus.souls.listener;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.qualityplus.assistant.base.event.EntityDamagedByPlayerEvent;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.souls.api.box.Box;
import com.qualityplus.souls.api.edition.SoulEdition;
import com.qualityplus.souls.base.gui.editgui.SoulsEditGUI;
import com.qualityplus.souls.base.soul.Soul;
import com.qualityplus.souls.persistance.data.SoulsData;
import de.tr7zw.changeme.nbtapi.NBTItem;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public final class SoulsDeleteListener implements Listener {
    private final Cache<UUID, String> clickCache = CacheBuilder.newBuilder()
            .concurrencyLevel(4)
            .maximumSize(1000)
            .expireAfterWrite(1, TimeUnit.SECONDS)
            .build();
    private @Inject SoulEdition edition;
    private @Inject Box box;

    @EventHandler
    public void onPlace(EntityDamagedByPlayerEvent e) {
        Player player = e.getPlayer();

        if(!player.isOp() && !player.hasPermission("souls.delete")) return;

        if(!(e.getEntity() instanceof ArmorStand)) return;

        ArmorStand armorStand = (ArmorStand) e.getEntity();

        if(!armorStand.hasMetadata("soulData")) return;

        Optional<Soul> soul = box.files().souls().getByLocation(armorStand.getLocation());

        if(!soul.isPresent()) return;

        e.setCancelled(true);

        String str = clickCache.getIfPresent(player.getUniqueId());

        if(str == null){
            clickCache.put(player.getUniqueId(), "");
            return;
        }

        player.openInventory(new SoulsEditGUI(box, soul.get(), edition).getInventory());
    }
}
