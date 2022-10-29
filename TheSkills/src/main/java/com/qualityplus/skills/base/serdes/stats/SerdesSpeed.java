package com.qualityplus.skills.base.serdes.stats;

import com.qualityplus.skills.base.serdes.SerdesStat;
import com.qualityplus.skills.base.stat.stats.PetLuckStat;
import com.qualityplus.skills.base.stat.stats.SpeedStat;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesSpeed implements ObjectSerializer<SpeedStat> {
    private static final SerdesStat SERDES_STAT = new SerdesStat();

    @Override
    public boolean supports(@NonNull Class<? super SpeedStat> type) {
        return SpeedStat.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull SpeedStat object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_STAT.serialize(object, data, generics);

        data.add("extraSpeedPercentagePerLevel", object.getExtraSpeedPercentagePerLevel(), Double.class);
    }

    @Override
    public SpeedStat deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        SpeedStat alchemySkill = new SpeedStat();

        SERDES_STAT.deserialize(alchemySkill, data, generics);

        alchemySkill.setExtraSpeedPercentagePerLevel(data.get("extraSpeedPercentagePerLevel", Double.class));

        return alchemySkill;
    }
}
