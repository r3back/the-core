package com.qualityplus.skills.persistance.data.user;

import com.qualityplus.assistant.api.data.Levellable;
import com.qualityplus.skills.base.stat.Stat;
import com.qualityplus.skills.base.stat.registry.Stats;
import com.qualityplus.skills.persistance.data.user.armor.LevellableArmorData;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.document.Document;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for user stats
 */
@Getter
@Setter
public final class UserStats extends Document implements Levellable<String, Integer>, LevellableArmorData<String> {
    private Map<String, Integer> level = new HashMap<>();
    private Map<String, Integer> fromArmor = new HashMap<>();

    /**
     * Adds a fill if empty
     */
    public void fillIfEmpty() {
        Stats.values().stream().map(Stat::getId).forEach(perk -> this.level.putIfAbsent(perk, 0));
        Stats.values().stream().map(Stat::getId).forEach(perk -> this.fromArmor.putIfAbsent(perk, 0));
    }

    /**
     * Adds a total level
     *
     * @param value Value
     * @return Total level
     */
    public int getTotalLevel(final String value) {
        return getLevel(value) + getArmor(value);
    }

    @Override
    public Integer getDefault() {
        return 0;
    }
}
