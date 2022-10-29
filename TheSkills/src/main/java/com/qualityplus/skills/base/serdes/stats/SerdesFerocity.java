package com.qualityplus.skills.base.serdes.stats;

import com.qualityplus.skills.base.serdes.SerdesStat;
import com.qualityplus.skills.base.stat.stats.DefenseStat;
import com.qualityplus.skills.base.stat.stats.FerocityStat;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesFerocity implements ObjectSerializer<FerocityStat> {
    private static final SerdesStat SERDES_STAT = new SerdesStat();

    @Override
    public boolean supports(@NonNull Class<? super FerocityStat> type) {
        return FerocityStat.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull FerocityStat object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_STAT.serialize(object, data, generics);

        data.add("chancePerLevel", object.getChancePerLevel(), Double.class);
    }

    @Override
    public FerocityStat deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        FerocityStat alchemySkill = new FerocityStat();

        SERDES_STAT.deserialize(alchemySkill, data, generics);

        alchemySkill.setChancePerLevel(data.get("chancePerLevel", Double.class));

        return alchemySkill;
    }
}
