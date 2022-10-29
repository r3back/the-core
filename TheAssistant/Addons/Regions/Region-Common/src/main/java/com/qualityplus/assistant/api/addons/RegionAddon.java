package com.qualityplus.assistant.api.addons;

import com.qualityplus.assistant.api.dependency.DependencyPlugin;
import org.bukkit.Location;

import java.util.List;
import java.util.Set;

public interface RegionAddon extends DependencyPlugin {
    default boolean isInAnyRegion(Location location, List<String> regions) {
        if(regions.isEmpty()) return false;

        return getRegions(location)
                .stream()
                .anyMatch(regions::contains);
    }

    default boolean isInRegion(Location location, String region) {
        if(region == null) return false;

        return getRegions(location)
                .stream()
                .anyMatch(region::equals);
    }

    Set<String> getRegions(Location location);
}
