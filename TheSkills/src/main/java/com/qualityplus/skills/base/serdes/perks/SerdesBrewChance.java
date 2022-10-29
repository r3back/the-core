package com.qualityplus.skills.base.serdes.perks;

import com.cryptomorin.xseries.XPotion;
import com.qualityplus.assistant.util.map.MapUtils;
import com.qualityplus.skills.base.serdes.SerdesPerk;
import com.qualityplus.skills.base.perk.perks.BrewChancePerk;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

import java.util.HashMap;

public final class SerdesBrewChance implements ObjectSerializer<BrewChancePerk> {
    private static final SerdesPerk SERDES_PERK = new SerdesPerk();

    @Override
    public boolean supports(@NonNull Class<? super BrewChancePerk> type) {
        return BrewChancePerk.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull BrewChancePerk object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_PERK.serialize(object, data, generics);

        data.addAsMap("potionAndChances", MapUtils.check(object.getPotionAndChances()), XPotion.class, Double.class);

        data.add("secondsDurationPerLevel", object.getSecondsDurationPerLevel(), Integer.class);
        data.add("baseSecondsDuration", object.getBaseSecondsDuration(), Integer.class);
    }

    @Override
    public BrewChancePerk deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        BrewChancePerk alchemySkill = new BrewChancePerk();

        SERDES_PERK.deserialize(alchemySkill, data, generics);

        alchemySkill.setPotionAndChances(data.containsKey("potionAndChances") ? data.getAsMap("potionAndChances", XPotion.class, Double.class) : new HashMap<>());

        alchemySkill.setSecondsDurationPerLevel(data.get("secondsDurationPerLevel", Integer.class));
        alchemySkill.setBaseSecondsDuration(data.get("baseSecondsDuration", Integer.class));

        return alchemySkill;
    }
}
