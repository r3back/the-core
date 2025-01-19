package com.qualityplus.pets.listener;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import com.qualityplus.pets.api.box.Box;
import com.qualityplus.pets.api.pet.entity.PetEntity;
import com.qualityplus.pets.base.pet.entity.tracker.PetArmorStandTracker;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.Optional;

@Component
public final class PetEntityListener implements Listener {
    private @Inject Box box;

    @EventHandler
    public void onJoin(final PlayerInteractAtEntityEvent event) {
        final Entity entity = event.getRightClicked();
        if (!(entity instanceof ArmorStand)) {
            return;
        }

        final ArmorStand armorStand = (ArmorStand) entity;

        final Optional<PetEntity> petEntity = PetArmorStandTracker.getByID(armorStand.getUniqueId());
        if (petEntity.isEmpty()) {
            return;
        }

        event.setCancelled(true);
    }
}