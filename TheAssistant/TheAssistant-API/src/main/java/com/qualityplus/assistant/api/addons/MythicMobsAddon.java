package com.qualityplus.assistant.api.addons;

import com.qualityplus.assistant.api.dependency.DependencyPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public interface MythicMobsAddon extends DependencyPlugin {
    boolean isMythicMob(Entity entity);
    String getInternalName(Entity entity);

    Entity spawn(String id, Location location, int level);
}
