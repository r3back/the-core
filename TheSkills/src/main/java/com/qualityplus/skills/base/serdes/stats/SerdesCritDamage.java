package com.qualityplus.skills.base.serdes.stats;

import com.qualityplus.skills.base.serdes.SerdesStat;
import com.qualityplus.skills.base.stat.stats.CritDamageStat;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesCritDamage implements ObjectSerializer<CritDamageStat> {
    private static final SerdesStat SERDES_STAT = new SerdesStat();

    @Override
    public boolean supports(@NonNull Class<? super CritDamageStat> type) {
        return CritDamageStat.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull CritDamageStat object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_STAT.serialize(object, data, generics);

        data.add("damagePercentagePerLevel", object.getDamagePercentagePerLevel(), Double.class);
        data.add("morePercentageBase", object.getMorePercentageBase(), Double.class);
    }

    @Override
    public CritDamageStat deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        CritDamageStat alchemySkill = new CritDamageStat();

        SERDES_STAT.deserialize(alchemySkill, data, generics);

        alchemySkill.setDamagePercentagePerLevel(data.get("damagePercentagePerLevel", Double.class));
        alchemySkill.setMorePercentageBase(data.get("morePercentageBase", Double.class));

        return alchemySkill;
    }
}
