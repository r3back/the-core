package com.qualityplus.skills.persistance.data.user;

import com.qualityplus.assistant.api.data.Levellable;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.perk.registry.Perks;
import com.qualityplus.skills.persistance.data.user.armor.LevellableArmorData;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.document.Document;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public final class UserPerks extends Document implements Levellable<String, Integer>, LevellableArmorData<String> {
    private Map<String, Integer> level = new HashMap<>();
    private Map<String, Double> fromArmor = new HashMap<>();

    public void fillIfEmpty() {
        Perks.values().stream().map(Perk::getId).forEach(perk -> level.putIfAbsent(perk, 0));
        Perks.values().stream().map(Perk::getId).forEach(perk -> fromArmor.putIfAbsent(perk, 0D));
    }

    @Override
    public Integer getDefault() {
        return 0;
    }

    @Override
    public Map<String, Double> getFromArmor() {
        return fromArmor;
    }
}