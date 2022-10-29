package com.qualityplus.skills.base.serdes.stats;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.map.MapUtils;
import com.qualityplus.skills.base.serdes.SerdesStat;
import com.qualityplus.skills.base.stat.stats.MagicFindStat;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

import java.util.HashMap;

public final class SerdesMagicFind implements ObjectSerializer<MagicFindStat> {
    private static final SerdesStat SERDES_STAT = new SerdesStat();

    @Override
    public boolean supports(@NonNull Class<? super MagicFindStat> type) {
        return MagicFindStat.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MagicFindStat object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_STAT.serialize(object, data, generics);

        data.addAsMap("itemAndChances", MapUtils.check(object.getItemAndChances()), XMaterial.class, Double.class);
        data.add("chancePerLevel", object.getChancePerLevel(), Double.class);
    }

    @Override
    public MagicFindStat deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        MagicFindStat alchemySkill = new MagicFindStat();

        SERDES_STAT.deserialize(alchemySkill, data, generics);

        alchemySkill.setItemAndChances(data.containsKey("itemAndChances") ? data.getAsMap("itemAndChances", XMaterial.class, Double.class) : new HashMap<>());

        alchemySkill.setChancePerLevel(data.get("chancePerLevel", Double.class));

        return alchemySkill;
    }
}
