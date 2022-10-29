package com.qualityplus.assistant.util.location;

import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

@AllArgsConstructor
@Getter
@Setter
public final class ALocation extends OkaeriConfig {
    private double x;
    private double y;
    private double z;
    private float pitch;
    private float yaw;
    private String world;

    public ALocation(Location location){
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.pitch = location.getPitch();
        this.yaw = location.getYaw();
        this.world = location.getWorld().getName();
    }

    public Location getLocation(){
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    public boolean equals(ALocation location){
        return location.getWorld().equals(world) &&
                location.getX() == x &&
                location.getY() == y &&
                location.getZ() == z &&
                location.getYaw() == yaw &&
                location.getPitch() == pitch;
    }
}
