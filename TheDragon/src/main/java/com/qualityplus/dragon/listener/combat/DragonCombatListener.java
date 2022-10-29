package com.qualityplus.dragon.listener.combat;

import com.cryptomorin.xseries.XSound;
import com.qualityplus.dragon.api.box.Box;
import com.qualityplus.dragon.api.game.dragon.TheDragonEntity;
import com.qualityplus.dragon.base.game.dragon.TheDragonEntityImpl;
import com.qualityplus.dragon.util.DragonHealthUtil;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import java.util.Optional;

@Component
public final class DragonCombatListener implements Listener {
    private @Inject Box box;

    @EventHandler
    public void removeDamageFromPlayers(EntityRegainHealthEvent e) {
        if(!(e.getEntity() instanceof EnderDragon) && !(e.getEntity() instanceof EnderDragonPart)) return;

        EnderDragon enderDragon = box.dragonService().getActiveEnderDragon();

        if(!e.getEntity().getUniqueId().equals(enderDragon.getUniqueId())) return;

        box.users().removePlayersDamage(e.getAmount());
    }

    @EventHandler(priority = EventPriority.LOW)
    public void addDamageToPlayers(EntityDamageByEntityEvent event) {
        if(!(event.getEntity() instanceof EnderDragon)) return;

        if(event.getDamager() instanceof Fireball){
            event.setCancelled(true);
        }else{
            Player attacker = getDragonAttacker(event);

            Optional.ofNullable(attacker).ifPresent(player -> {
                double damage = event.getDamage();

                if(dragonIsDeath()) return;

                addDamageToAttacker(player, damage);

                mangeDragonHealth(event, damage);
            });
        }
    }

    private void mangeDragonHealth(EntityDamageByEntityEvent event, double damage){
        TheDragonEntity theDragonEntity = box.dragonService().getActiveDragon();

        EnderDragon enderDragon = box.dragonService().getActiveEnderDragon();

        if(enderDragon.getHealth() <= 0) return;

        event.setCancelled(true);

        double dragonHealth = DragonHealthUtil.getHealthAfterDamage(theDragonEntity.getHealth(), enderDragon.getHealth(), damage);

        if(dragonHealth < 0){
            box.game().finish();
        }else{
            enderDragon.setHealth(dragonHealth);

            enderDragon.playEffect(EntityEffect.HURT);
            enderDragon.getWorld().playSound(enderDragon.getLocation(), XSound.ENTITY_ENDER_DRAGON_HURT.parseSound(), 100.0F, 1.0F);
        }
    }

    private boolean dragonIsDeath(){
        TheDragonEntity theDragonEntity = box.dragonService().getActiveDragon();

        EnderDragon enderDragon = box.dragonService().getActiveEnderDragon();

        return !(theDragonEntity instanceof TheDragonEntityImpl) || enderDragon.getHealth() <= 0;
    }

    private Player getDragonAttacker(EntityDamageByEntityEvent event){
        return event.getDamager() instanceof Player ? (Player) event.getDamager() : getPlayerIfIsShooter(event);
    }

    private Player getPlayerIfIsShooter(EntityDamageByEntityEvent event){
        return event.getDamager() instanceof Arrow && ((Arrow)event.getDamager()).getShooter() instanceof Player ?
                (Player) ((Arrow)event.getDamager()).getShooter() : null;
    }

    private void addDamageToAttacker(Player player, double damage){
        box.users().addPlayerDamage(player, damage);
        box.users().setLast(player.getUniqueId());
    }
}
