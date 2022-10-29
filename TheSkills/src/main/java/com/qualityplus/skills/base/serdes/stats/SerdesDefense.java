package com.qualityplus.skills.base.serdes.stats;

import com.qualityplus.skills.base.serdes.SerdesStat;
import com.qualityplus.skills.base.stat.stats.CritDamageStat;
import com.qualityplus.skills.base.stat.stats.DefenseStat;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesDefense implements ObjectSerializer<DefenseStat> {
    private static final SerdesStat SERDES_STAT = new SerdesStat();

    @Override
    public boolean supports(@NonNull Class<? super DefenseStat> type) {
        return DefenseStat.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull DefenseStat object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_STAT.serialize(object, data, generics);

        data.add("damageReductionPercentagePerLevel", object.getDamageReductionPercentagePerLevel(), Double.class);
    }

    @Override
    public DefenseStat deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        DefenseStat alchemySkill = new DefenseStat();

        SERDES_STAT.deserialize(alchemySkill, data, generics);

        alchemySkill.setDamageReductionPercentagePerLevel(data.get("damageReductionPercentagePerLevel", Double.class));

        return alchemySkill;
    }
}
