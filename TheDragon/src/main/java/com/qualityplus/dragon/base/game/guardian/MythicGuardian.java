package com.qualityplus.dragon.base.game.guardian;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.dragon.api.game.guardian.Guardian;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

@AllArgsConstructor
@Getter
public final class MythicGuardian extends OkaeriConfig implements Guardian {
    private final String name;
    private final int level;

    @Override
    public Entity spawn(Location location) {
        return TheAssistantPlugin.getAPI().getAddons().getMythicMobs().spawn(name, location, 1);
    }

    @Override
    public String getID() {
        return name;
    }
}
