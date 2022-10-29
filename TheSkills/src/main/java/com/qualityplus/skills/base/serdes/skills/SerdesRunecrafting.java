package com.qualityplus.skills.base.serdes.skills;

import com.qualityplus.skills.base.serdes.SerdesSkills;
import com.qualityplus.skills.base.skill.skills.RunecraftingSkill;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesRunecrafting implements ObjectSerializer<RunecraftingSkill> {
    private static final SerdesSkills SERDES_SKILLS = new SerdesSkills();

    @Override
    public boolean supports(@NonNull Class<? super RunecraftingSkill> type) {
        return RunecraftingSkill.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull RunecraftingSkill object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_SKILLS.serialize(object, data, generics);

        data.add("reward", object.getReward(), Double.class);
    }

    @Override
    public RunecraftingSkill deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        RunecraftingSkill runecraftingSkill = new RunecraftingSkill();

        SERDES_SKILLS.deserialize(runecraftingSkill, data, generics);

        runecraftingSkill.setReward(data.get("reward", Double.class));

        return runecraftingSkill;
    }
}
