package com.qualityplus.assistant.listener;

import com.qualityplus.assistant.base.event.EntityDamagedByPlayerEvent;
import com.qualityplus.assistant.base.event.PlayerDamagedByEntityEvent;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@Component
public final class DamageListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void entityDamagedByPlayerEvent(EntityDamageByEntityEvent e){
        if(!(e.getDamager() instanceof Player)) return;

        EntityDamagedByPlayerEvent event = new EntityDamagedByPlayerEvent((Player) e.getDamager(), e.getEntity());

        Bukkit.getPluginManager().callEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void playerDamagedByEntityEvent(EntityDamageByEntityEvent e){
        if(!(e.getEntity() instanceof Player)) return;

        PlayerDamagedByEntityEvent event = new PlayerDamagedByEntityEvent((Player) e.getEntity(), e.getDamager());

        Bukkit.getPluginManager().callEvent(event);
    }
}
