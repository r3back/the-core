package com.qualityplus.skills.base.serdes.skills;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.map.MapUtils;
import com.qualityplus.skills.base.serdes.SerdesSkills;
import com.qualityplus.skills.base.skill.skills.MiningSkill;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

import java.util.HashMap;

public final class SerdesMining implements ObjectSerializer<MiningSkill> {
    private static final SerdesSkills SERDES_SKILLS = new SerdesSkills();

    @Override
    public boolean supports(@NonNull Class<? super MiningSkill> type) {
        return MiningSkill.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MiningSkill object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_SKILLS.serialize(object, data, generics);

        data.addAsMap("rewards", MapUtils.check(object.getRewards()), XMaterial.class, Double.class);
    }

    @Override
    public MiningSkill deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        MiningSkill miningSkill = new MiningSkill();

        SERDES_SKILLS.deserialize(miningSkill, data, generics);

        miningSkill.setRewards(data.containsKey("rewards") ? data.getAsMap("rewards", XMaterial.class, Double.class) : new HashMap<>());

        return miningSkill;
    }
}
