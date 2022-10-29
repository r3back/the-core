package com.qualityplus.skills.persistance.data.user;

import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.perk.registry.Perks;
import com.qualityplus.skills.persistance.data.user.armor.LevellableArmorData;
import com.qualityplus.assistant.api.common.data.LevellableInteger;
import eu.okaeri.persistence.document.Document;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public final class UserPerks extends Document implements LevellableInteger<String>, LevellableArmorData<String> {
    private Map<String, Integer> level = new HashMap<>();
    private Map<String, Integer> fromArmor = new HashMap<>();

    public void fillIfEmpty(){
        Perks.values().stream().map(Perk::getId).forEach(perk -> level.putIfAbsent(perk, 0));
        Perks.values().stream().map(Perk::getId).forEach(perk -> fromArmor.putIfAbsent(perk, 0));
    }
}