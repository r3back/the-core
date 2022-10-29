package com.qualityplus.skills.base.serdes.skills;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.map.MapUtils;
import com.qualityplus.skills.base.serdes.SerdesSkills;
import com.qualityplus.skills.base.skill.skills.AlchemySkill;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

import java.util.HashMap;

public final class SerdesAlchemy  implements ObjectSerializer<AlchemySkill> {
    private static final SerdesSkills SERDES_SKILLS = new SerdesSkills();

    @Override
    public boolean supports(@NonNull Class<? super AlchemySkill> type) {
        return AlchemySkill.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull AlchemySkill object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_SKILLS.serialize(object, data, generics);

        data.addAsMap("rewards", MapUtils.check(object.getRewards()), XMaterial.class, Double.class);
    }

    @Override
    public AlchemySkill deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        AlchemySkill alchemySkill = new AlchemySkill();

        SERDES_SKILLS.deserialize(alchemySkill, data, generics);

        alchemySkill.setRewards(data.containsKey("rewards") ? data.getAsMap("rewards", XMaterial.class, Double.class) : new HashMap<>());

        return alchemySkill;
    }
}
