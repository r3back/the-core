package com.qualityplus.skills.base.serdes.stats;

import com.qualityplus.skills.base.serdes.SerdesStat;
import com.qualityplus.skills.base.stat.stats.PetLuckStat;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesPetLuck implements ObjectSerializer<PetLuckStat> {
    private static final SerdesStat SERDES_STAT = new SerdesStat();

    @Override
    public boolean supports(@NonNull Class<? super PetLuckStat> type) {
        return PetLuckStat.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull PetLuckStat object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_STAT.serialize(object, data, generics);

    }

    @Override
    public PetLuckStat deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        PetLuckStat alchemySkill = new PetLuckStat();

        SERDES_STAT.deserialize(alchemySkill, data, generics);

        return alchemySkill;
    }
}
