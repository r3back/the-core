package com.qualityplus.dragon.base.game.structure;

import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Exclude;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.dragon.VoxDragon;
import com.qualityplus.dragon.api.game.structure.type.DragonCrystal;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Optional;

@RequiredArgsConstructor
public final class DragonCrystalImpl extends OkaeriConfig implements DragonCrystal {
    @Exclude
    private EnderCrystal enderCrystal;
    @Getter
    private final Location location;
    @Getter
    private final String worldName;

    public DragonCrystalImpl(final Location location) {
        this.location = location;
        if (location != null && location.getWorld() != null) {
            this.worldName = location.getWorld().getName();
        } else {
            this.worldName = "world";
        }
    }

    @Override
    public void removeStructure() {
        Optional.ofNullable(enderCrystal).ifPresent(EnderCrystal::remove);
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(worldName), location.getX(), location.getY(), location.getZ());
    }

    @Override
    public World getWorld() {
        return Bukkit.getWorld(worldName);
    }

    @Override
    public void spawn() {
        final Location loc = getLocation();
        if (loc == null) {
            Bukkit.getConsoleSender().sendMessage(StringUtils.color("&c[TheDragon] Failed to add dragon crystals location is null!"));
            return;
        }

        World world1 = loc.getWorld();

        if (world1 == null) {
            Bukkit.getConsoleSender().sendMessage(StringUtils.color("&c[TheDragon] Failed to add dragon crystals world is null!"));
            return;
        }

        Bukkit.getScheduler().runTask(VoxDragon.getApi().getPlugin(), () -> {
            try {
                enderCrystal = loc.getWorld().spawn(loc.clone().add(0.0D, 1.0D, 0.0D), EnderCrystal.class);

                enderCrystal.setMetadata("theDragonCrystal", new FixedMetadataValue(VoxDragon.getApi().getPlugin(), "theDragonCrystal"));
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(StringUtils.color("&c[TheDragon] Failed to add dragon crystals TASK EXCEPTION!"));
            }
        });
    }
}
