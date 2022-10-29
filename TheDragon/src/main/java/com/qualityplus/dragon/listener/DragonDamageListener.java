package com.qualityplus.dragon.listener;

import com.qualityplus.dragon.api.box.Box;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.projectiles.ProjectileSource;

@Component
public final class DragonDamageListener implements Listener {
    private @Inject Box box;

    @EventHandler
    public void makeDragonShoot(EntityTargetEvent e) {
        if(!isEnderDragon(e.getEntity())) return;

        Entity dragon = e.getEntity();

        Location location = dragon.getLocation();
        Fireball fireball = dragon.getWorld().spawn(location, Fireball.class);
        fireball.setShooter((ProjectileSource) dragon);
        fireball.setVelocity(dragon.getLocation().getDirection().multiply(2));
        fireball.setIsIncendiary(false);
        fireball.setYield(0);
    }


    @EventHandler
    public void onDragonDestroyBlocks(EntityChangeBlockEvent e) {
        if(!isEnderDragon(e.getEntity())) return;

        e.setCancelled(true);
    }

    @EventHandler
    public void onDragonExplodeBlocks(EntityExplodeEvent e) {
        if(!isEnderDragon(e.getEntity())) return;

        e.setCancelled(true);
    }

    @EventHandler
    public void stopDragonDamage(EntityExplodeEvent event) {
        Entity entity = event.getEntity();

        if(!isEnderDragon(entity) && !(entity instanceof EnderDragonPart)) return;

        event.setCancelled(true);
    }


    @EventHandler
    public void cancelDragonRegeneration(EntityRegainHealthEvent e) {
        if(!isEnderDragon(e.getEntity())) return;

        if(((EnderDragon) e.getEntity()).getHealth() >= 2) return;

        e.setCancelled(true);
    }

    @EventHandler
    public void finishGameOnDeath(EntityDeathEvent e) {
        if(!isEnderDragon(e.getEntity())) return;

        box.game().finish();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onExplode(EntityExplodeEvent e) {
        if(!(e.getEntity() instanceof Fireball)) return;

        Fireball fireball = (Fireball) e.getEntity();

        if(!(fireball.getShooter() instanceof EnderDragon)) return;

        if(!box.game().isActive()) return;

        e.setCancelled(true);

        Location location = fireball.getLocation().clone();

        fireball.remove();

        replaceForFakeExplosion(location);
    }

    private void replaceForFakeExplosion(Location location){
        if(location.getWorld() == null) return;

        location.getWorld().createExplosion(location.getX(), location.getY(), location.getZ(), 5, false, false);
    }


    @EventHandler
    public void onCrystalExplode(EntityDamageEvent e) {
        if(!(e.getEntity() instanceof EnderCrystal)) return;

        if(!box.structures().isCrystal(e.getEntity())) return;

        if(!box.files().config().eventSettings.generalSettings.invincibleCrystals) return;

        e.setCancelled(true);
    }

    @EventHandler
    public void onDragonExplode(EntityDamageEvent e) {
        if(!isEnderDragon(e.getEntity())) return;

        if(e.getCause() != EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) return;

        e.setCancelled(true);
    }


    private boolean isEnderDragon(Entity entity){
        return entity instanceof EnderDragon;
    }
}