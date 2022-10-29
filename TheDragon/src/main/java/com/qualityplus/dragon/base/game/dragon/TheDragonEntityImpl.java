package com.qualityplus.dragon.base.game.dragon;

import com.qualityplus.dragon.api.game.dragon.TheDragonEntity;
import com.qualityplus.dragon.util.DragonHealthUtil;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Exclude;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.EnderDragon;

import java.util.Optional;

@Getter
public final class TheDragonEntityImpl extends OkaeriConfig implements TheDragonEntity {
    private @Exclude EnderDragon enderDragon;
    private final String displayName;
    private final double maxHealth;
    private final double chance;
    private final double xp;
    private final String id;

    public TheDragonEntityImpl(String id, String displayName, double maxHealth, double chance, double xp) {
        this.displayName = displayName;
        this.maxHealth = maxHealth;
        this.chance = chance;
        this.id = id;
        this.xp = xp;
    }

    @Override
    public EnderDragon spawn(Location location) {
        enderDragon = Optional.ofNullable(location.getWorld())
                .map(world -> world.spawn(location, EnderDragon.class))
                .orElse(null);

        return enderDragon;
    }

    @Override
    public double getHealth() {
        return Optional.ofNullable(enderDragon)
                .map(dragon -> DragonHealthUtil.getHealth(maxHealth, dragon.getHealth()))
                .orElse(0.0);
    }

}
