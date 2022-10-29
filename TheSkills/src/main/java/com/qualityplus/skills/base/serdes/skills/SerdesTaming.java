package com.qualityplus.skills.base.serdes.skills;

import com.qualityplus.skills.base.serdes.SerdesSkills;
import com.qualityplus.skills.base.skill.skills.TamingSkill;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesTaming implements ObjectSerializer<TamingSkill> {
    private static final SerdesSkills SERDES_SKILLS = new SerdesSkills();


    @Override
    public boolean supports(@NonNull Class<? super TamingSkill> type) {
        return TamingSkill.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull TamingSkill object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_SKILLS.serialize(object, data, generics);
    }

    @Override
    public TamingSkill deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        TamingSkill tamingSkill = new TamingSkill();

        SERDES_SKILLS.deserialize(tamingSkill, data, generics);

        return tamingSkill;
    }
}
