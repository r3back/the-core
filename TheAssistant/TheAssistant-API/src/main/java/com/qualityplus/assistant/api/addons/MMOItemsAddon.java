package com.qualityplus.assistant.api.addons;

import com.qualityplus.assistant.api.dependency.DependencyPlugin;

import java.util.UUID;

public interface MMOItemsAddon extends DependencyPlugin {
    void updateStats(UUID uuid, String ability, String type, double value);

    double getStats(UUID uuid, String ability);

    double getMMOArmor(UUID uuid, String ability);
}
