package com.qualityplus.dragon.base.game.structure;

import com.qualityplus.dragon.api.game.structure.type.DragonSpawn;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

@Getter
@AllArgsConstructor
public final class DragonSpawnImpl extends OkaeriConfig implements DragonSpawn {
    private Location location;
    @Getter
    private final String worldName;

    public DragonSpawnImpl(final Location location) {
        this.location = location;
        if (location != null && location.getWorld() != null) {
            this.worldName = location.getWorld().getName();
        } else {
            this.worldName = "world";
        }
    }

    @Override
    public void removeStructure() {
        /**
         * TODO
         */
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(worldName), location.getX(), location.getY(), location.getZ());
    }

    @Override
    public World getWorld() {
        return Bukkit.getWorld(worldName);
    }
}
