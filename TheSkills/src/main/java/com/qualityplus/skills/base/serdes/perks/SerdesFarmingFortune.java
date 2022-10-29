package com.qualityplus.skills.base.serdes.perks;

import com.qualityplus.skills.base.perk.perks.FarmingFortunePerk;
import com.qualityplus.skills.base.serdes.perks.common.SerdesFortunePerk;
import com.qualityplus.skills.base.serdes.SerdesPerk;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesFarmingFortune implements ObjectSerializer<FarmingFortunePerk> {
    private static final SerdesPerk SERDES_PERK = new SerdesPerk();
    private static final SerdesFortunePerk SERDES_FORTUNE_PERK = new SerdesFortunePerk();

    @Override
    public boolean supports(@NonNull Class<? super FarmingFortunePerk> type) {
        return FarmingFortunePerk.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull FarmingFortunePerk object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_PERK.serialize(object, data, generics);
        SERDES_FORTUNE_PERK.serialize(object, data, generics);

        data.add("initialAmount", object.getInitialAmount(), Double.class);
        data.add("chancePerLevel", object.getChancePerLevel(), Double.class);
    }

    @Override
    public FarmingFortunePerk deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        FarmingFortunePerk alchemySkill = new FarmingFortunePerk();

        SERDES_PERK.deserialize(alchemySkill, data, generics);
        SERDES_FORTUNE_PERK.deserialize(alchemySkill, data, generics);

        alchemySkill.setChancePerLevel(data.get("chancePerLevel", Double.class));
        alchemySkill.setInitialAmount(data.get("initialAmount", Double.class));

        return alchemySkill;
    }
}
