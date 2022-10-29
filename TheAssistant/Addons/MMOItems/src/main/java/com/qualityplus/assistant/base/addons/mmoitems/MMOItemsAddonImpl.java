package com.qualityplus.assistant.base.addons.mmoitems;

import com.qualityplus.assistant.api.addons.MMOItemsAddon;
import io.lumine.mythic.lib.api.player.MMOPlayerData;
import io.lumine.mythic.lib.api.stat.StatInstance;
import io.lumine.mythic.lib.api.stat.StatMap;
import io.lumine.mythic.lib.api.stat.modifier.StatModifier;

import java.util.Optional;
import java.util.UUID;

public final class MMOItemsAddonImpl implements MMOItemsAddon {
    @Override
    public String getAddonName() {
        return "MMOItems";
    }

    @Override
    public void updateStats(UUID uuid, String ability, String type, double value) {
        StatMap stats = MMOPlayerData.get(uuid).getStatMap();

        StatInstance attribute = stats.getInstance(ability);

        String key = "HC_" + ability + type;

        StatModifier modifier = attribute.getModifier(key);

        if (modifier == null || modifier.getValue() != value)
            attribute.addModifier(new StatModifier(key, type, value));
    }

    @Override
    public double getStats(UUID uuid, String ability) {
        return Optional.ofNullable(MMOPlayerData.get(uuid))
                .map(data -> data.getStatMap().getStat(ability))
                .orElse(0D);
    }

    @Override
    public double getMMOArmor(UUID uuid, String ability) {
        return Optional.ofNullable(MMOPlayerData.get(uuid))
                .map(data -> Optional.ofNullable(data.getStatMap()
                        .getInstance(ability)
                        .getModifier("MMOItem"))
                        .map(StatModifier::getValue)
                        .orElse(0D))
                .orElse(0D);
    }
}
