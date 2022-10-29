package com.qualityplus.dragon.base.game.structure;

import com.qualityplus.dragon.api.game.structure.type.DragonSpawn;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;

@AllArgsConstructor
public final class DragonSpawnImpl extends OkaeriConfig implements DragonSpawn {
    private @Getter Location location;

    @Override
    public void removeStructure() {
        /**
         * TODO
         */
    }

    @Override
    public World getWorld() {
        return location.getWorld();
    }
}
