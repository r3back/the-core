package com.qualityplus.souls.base.config;

import com.qualityplus.souls.base.soul.Soul;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration(path = "souls.yml")
@Header("================================")
@Header("       Souls      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Souls extends OkaeriConfig {
    public final List<Soul> soulList = new ArrayList<>();

    public Optional<Soul> getByLocation(Location location){
        return soulList.stream().filter(soul -> soul.getLocation().getLocation().equals(location)).findFirst();
    }
}
