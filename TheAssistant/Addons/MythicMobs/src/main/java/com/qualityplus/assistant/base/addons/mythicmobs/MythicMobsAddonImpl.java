package com.qualityplus.assistant.base.addons.mythicmobs;

import com.qualityplus.assistant.api.addons.MythicMobsAddon;
import io.lumine.mythic.bukkit.BukkitAPIHelper;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public final class MythicMobsAddonImpl implements MythicMobsAddon {
    private final BukkitAPIHelper bukkitAPIHelper = new BukkitAPIHelper();

    @Override
    public boolean isMythicMob(Entity entity) {
        try {
            ActiveMob activeMob = bukkitAPIHelper.getMythicMobInstance(entity);
            return activeMob != null;
        }catch (NullPointerException e){
            return false;
        }
    }

    @Override
    public String getInternalName(Entity entity) {
        try {
            ActiveMob activeMob = bukkitAPIHelper.getMythicMobInstance(entity);

            if(activeMob == null) return null;

            return activeMob.getType().getInternalName();
        }catch (NullPointerException e){
            return null;
        }
    }

    @Override
    public Entity spawn(String id, Location location, int level) {
        /*
        TODO ADD SPAWN LOGIC
         */
        return null;
    }

    @Override
    public String getAddonName() {
        return "MythicMobs";
    }
}
