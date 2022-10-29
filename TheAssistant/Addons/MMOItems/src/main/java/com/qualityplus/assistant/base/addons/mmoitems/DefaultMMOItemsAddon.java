package com.qualityplus.assistant.base.addons.mmoitems;

import com.qualityplus.assistant.api.addons.MMOItemsAddon;

import java.util.UUID;

public final class DefaultMMOItemsAddon implements MMOItemsAddon {
    @Override
    public String getAddonName() {
        return null;
    }

    @Override
    public void updateStats(UUID uuid, String ability, String type, double value) {

    }

    @Override
    public double getStats(UUID uuid, String ability) {
        return 0;
    }

    @Override
    public double getMMOArmor(UUID uuid, String ability) {
        return 0;
    }
}
