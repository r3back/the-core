package com.qualityplus.dragon.base.game.structure;

import com.qualityplus.dragon.TheDragon;
import com.qualityplus.dragon.api.game.structure.type.DragonCrystal;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Optional;

@RequiredArgsConstructor
public final class DragonCrystalImpl extends OkaeriConfig implements DragonCrystal {
    private EnderCrystal enderCrystal;
    private @Getter final Location location;

    @Override
    public void removeStructure() {
        Optional.ofNullable(enderCrystal).ifPresent(EnderCrystal::remove);
    }

    @Override
    public World getWorld() {
        return location.getWorld();
    }

    @Override
    public void spawn() {
        enderCrystal = getLocation().getWorld().spawn(getLocation().clone().add(0.0D, 1.0D, 0.0D), EnderCrystal.class);
        enderCrystal.setMetadata("theDragonCrystal", new FixedMetadataValue(TheDragon.getApi().getPlugin(), "theDragonCrystal"));
    }
}
