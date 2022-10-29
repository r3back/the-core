package com.qualityplus.skills.base.serdes.skills;

import com.qualityplus.assistant.util.map.MapUtils;
import com.qualityplus.skills.base.serdes.SerdesSkills;
import com.qualityplus.skills.base.skill.skills.FishingSkill;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

import java.util.HashMap;

public final class SerdesFishing implements ObjectSerializer<FishingSkill> {
    private static final SerdesSkills SERDES_SKILLS = new SerdesSkills();

    @Override
    public boolean supports(@NonNull Class<? super FishingSkill> type) {
        return FishingSkill.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull FishingSkill object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_SKILLS.serialize(object, data, generics);

        data.addAsMap("rewards", MapUtils.check(object.getRewards()), String.class, Double.class);

        data.add("rewardsForAllCaught", object.getRewardsForAllCaught(), Double.class);
    }

    @Override
    public FishingSkill deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        FishingSkill fishingSkill = new FishingSkill();

        SERDES_SKILLS.deserialize(fishingSkill, data, generics);

        fishingSkill.setRewards(data.containsKey("rewards") ? data.getAsMap("rewards", String.class, Double.class) : new HashMap<>());

        fishingSkill.setRewardsForAllCaught(data.get("rewardsForAllCaught", Double.class));

        return fishingSkill;
    }
}
