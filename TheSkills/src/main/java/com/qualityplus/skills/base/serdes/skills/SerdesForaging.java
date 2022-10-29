package com.qualityplus.skills.base.serdes.skills;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.map.MapUtils;
import com.qualityplus.skills.base.serdes.SerdesSkills;
import com.qualityplus.skills.base.skill.skills.ForagingSkill;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

import java.util.HashMap;

public final class SerdesForaging implements ObjectSerializer<ForagingSkill> {
    private static final SerdesSkills SERDES_SKILLS = new SerdesSkills();

    @Override
    public boolean supports(@NonNull Class<? super ForagingSkill> type) {
        return ForagingSkill.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull ForagingSkill object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_SKILLS.serialize(object, data, generics);

        data.addAsMap("rewards", MapUtils.check(object.getRewards()), XMaterial.class, Double.class);
    }

    @Override
    public ForagingSkill deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        ForagingSkill foragingSkill = new ForagingSkill();

        SERDES_SKILLS.deserialize(foragingSkill, data, generics);

        foragingSkill.setRewards(data.containsKey("rewards") ? data.getAsMap("rewards", XMaterial.class, Double.class) : new HashMap<>());

        return foragingSkill;
    }
}
