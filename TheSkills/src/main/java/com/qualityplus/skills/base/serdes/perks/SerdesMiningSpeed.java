package com.qualityplus.skills.base.serdes.perks;

import com.qualityplus.skills.base.serdes.SerdesPerk;
import com.qualityplus.skills.base.perk.perks.MiningSpeedPerk;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesMiningSpeed implements ObjectSerializer<MiningSpeedPerk> {
    private static final SerdesPerk SERDES_PERK = new SerdesPerk();

    @Override
    public boolean supports(@NonNull Class<? super MiningSpeedPerk> type) {
        return MiningSpeedPerk.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MiningSpeedPerk object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_PERK.serialize(object, data, generics);

        data.add("secondsDurationPerLevel", object.getSecondsDurationPerLevel(), Integer.class);
        data.add("baseSecondsDuration", object.getBaseSecondsDuration(), Integer.class);
        data.add("chancePerLevel", object.getChancePerLevel(), Double.class);
    }

    @Override
    public MiningSpeedPerk deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        MiningSpeedPerk alchemySkill = new MiningSpeedPerk();

        SERDES_PERK.deserialize(alchemySkill, data, generics);

        alchemySkill.setSecondsDurationPerLevel(data.get("secondsDurationPerLevel", Integer.class));
        alchemySkill.setBaseSecondsDuration(data.get("baseSecondsDuration", Integer.class));
        alchemySkill.setChancePerLevel(data.get("chancePerLevel", Double.class));

        return alchemySkill;
    }
}
