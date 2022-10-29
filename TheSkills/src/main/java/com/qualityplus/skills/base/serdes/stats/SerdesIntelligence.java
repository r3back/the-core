package com.qualityplus.skills.base.serdes.stats;

import com.qualityplus.skills.base.serdes.SerdesStat;
import com.qualityplus.skills.base.stat.stats.FerocityStat;
import com.qualityplus.skills.base.stat.stats.IntelligenceStat;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesIntelligence implements ObjectSerializer<IntelligenceStat> {
    private static final SerdesStat SERDES_STAT = new SerdesStat();

    @Override
    public boolean supports(@NonNull Class<? super IntelligenceStat> type) {
        return IntelligenceStat.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull IntelligenceStat object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_STAT.serialize(object, data, generics);

    }

    @Override
    public IntelligenceStat deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        IntelligenceStat alchemySkill = new IntelligenceStat();

        SERDES_STAT.deserialize(alchemySkill, data, generics);

        return alchemySkill;
    }
}
