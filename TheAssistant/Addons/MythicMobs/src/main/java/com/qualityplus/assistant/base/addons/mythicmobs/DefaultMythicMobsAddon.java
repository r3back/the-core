package com.qualityplus.assistant.base.addons.mythicmobs;

import com.qualityplus.assistant.api.addons.MythicMobsAddon;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public final class DefaultMythicMobsAddon implements MythicMobsAddon {
    @Override
    public boolean isMythicMob(Entity entity) {
        return false;
    }

    @Override
    public String getInternalName(Entity entity) {
        return null;
    }

    @Override
    public Entity spawn(String id, Location location, int level) {
        return null;
    }

    @Override
    public String getAddonName() {
        return null;
    }
}
