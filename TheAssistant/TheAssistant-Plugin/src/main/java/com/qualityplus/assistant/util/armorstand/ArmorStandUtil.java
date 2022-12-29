package com.qualityplus.assistant.util.armorstand;

import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@UtilityClass
public class ArmorStandUtil {
    public void rotate(ArmorStand armorStand, Location newLocation){
        Location location = armorStand.getLocation().clone();

        armorStand.teleport(location.clone().setDirection(newLocation.clone().subtract(location).toVector()));
    }

    public boolean entityIsValid(ArmorStand armorStand){
        return armorStand != null && !armorStand.isDead();
    }

    public ArmorStand createDefault(Location location){
        return Optional.ofNullable(location.getWorld())
                .map(world -> getArmorStand(world, location))
                .orElse(null);
    }

    private ArmorStand getArmorStand(@NotNull World world, Location location){
        ArmorStand armorStand = world.spawn(location, ArmorStand.class);
        armorStand.setArms(true);
        armorStand.setSmall(true);
        armorStand.setVisible(true);
        armorStand.setInvulnerable(true);
        armorStand.setGravity(false);
        armorStand.setBasePlate(false);
        armorStand.setCollidable(false);
        armorStand.setCustomNameVisible(false);
        return armorStand;
    }
}
