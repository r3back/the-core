package com.qualityplus.minions.listener;

import com.qualityplus.minions.api.box.Box;
import com.qualityplus.minions.api.minion.MinionEntity;
import com.qualityplus.minions.base.gui.main.MainMinionGUI;
import com.qualityplus.minions.base.minions.entity.tracker.MinionArmorStandTracker;

import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.Optional;

@Component
public final class MinionInteractListener implements Listener {
    private @Inject Box box;

    @EventHandler
    private void onClick(PlayerInteractAtEntityEvent event){

        if(!(event.getRightClicked() instanceof ArmorStand)) return;

        Player player = event.getPlayer();

        Optional<MinionEntity> armorStand = MinionArmorStandTracker.getByID(event.getRightClicked().getUniqueId());

        if(!armorStand.isPresent()) return;

        event.setCancelled(true);

        player.openInventory(new MainMinionGUI(box, armorStand.get()).getInventory());
    }

    @EventHandler
    private void onClick(EntityDamageByEntityEvent event){
        Entity entity = event.getEntity();

        if(!(entity instanceof ArmorStand)) return;

        Optional<MinionEntity> minionEntity = MinionArmorStandTracker.getByID(entity.getUniqueId());

        if(!minionEntity.isPresent()) return;

        event.setCancelled(true);
    }
}