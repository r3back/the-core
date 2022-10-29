package com.qualityplus.assistant.base.addons.regions;

import com.qualityplus.assistant.api.addons.RegionAddon;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Location;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class WG7Addon implements RegionAddon {
    private final RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();

    @Override
    public String getAddonName() {
        return "World Guard 7";
    }

    @Override
    public Set<String> getRegions(Location location) {
        World weWorld = Optional
                .ofNullable(location.getWorld()).map(BukkitAdapter::adapt)
                .orElse(null);

        return container.get(weWorld) == null ? new HashSet<>() : container.createQuery()
                .getApplicableRegions(BukkitAdapter.adapt(location))
                .getRegions()
                .stream()
                .map(ProtectedRegion::getId)
                .collect(Collectors.toSet());
    }
}
