package com.qualityplus.assistant.base.addons.regions;

//import com.bekvon.bukkit.residence.Residence;
import com.qualityplus.assistant.api.addons.RegionAddon;
import org.bukkit.Location;

import java.util.HashSet;
import java.util.Set;

public final class ResidenceAddon implements RegionAddon {
    @Override
    public Set<String> getRegions(Location location) {
        return new HashSet<>()/*Optional.ofNullable(Residence.getInstance().getResidenceManager().getByLoc(location))
                .map(claimedResidence -> new HashSet<>(Collections.singletonList(claimedResidence.getName())))
                .orElseGet(HashSet::new)*/;
    }

    @Override
    public String getAddonName() {
        return "Residence";
    }
}
