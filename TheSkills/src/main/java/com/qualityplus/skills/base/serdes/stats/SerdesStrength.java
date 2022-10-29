package com.qualityplus.skills.base.serdes.stats;

import com.qualityplus.skills.base.serdes.SerdesStat;
import com.qualityplus.skills.base.stat.stats.StrengthStat;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesStrength implements ObjectSerializer<StrengthStat> {
    private static final SerdesStat SERDES_STAT = new SerdesStat();

    @Override
    public boolean supports(@NonNull Class<? super StrengthStat> type) {
        return StrengthStat.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull StrengthStat object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_STAT.serialize(object, data, generics);
    }

    @Override
    public StrengthStat deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        StrengthStat alchemySkill = new StrengthStat();

        SERDES_STAT.deserialize(alchemySkill, data, generics);

        return alchemySkill;
    }
}
