package com.qualityplus.skills.base.serdes.skills;

import com.qualityplus.assistant.util.map.MapUtils;
import com.qualityplus.skills.base.serdes.SerdesSkills;
import com.qualityplus.skills.base.skill.skills.DungeoneeringSkill;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

import java.util.HashMap;

public final class SerdesDuneoneering implements ObjectSerializer<DungeoneeringSkill> {
    private static final SerdesSkills SERDES_SKILLS = new SerdesSkills();

    @Override
    public boolean supports(@NonNull Class<? super DungeoneeringSkill> type) {
        return DungeoneeringSkill.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull DungeoneeringSkill object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_SKILLS.serialize(object, data, generics);

        data.addAsMap("rewardsByName", MapUtils.check(object.getRewardsByName()), String.class, Double.class);
        data.addAsMap("mythicMobRewards", MapUtils.check(object.getMythicMobRewards()), String.class, Double.class);
    }

    @Override
    public DungeoneeringSkill deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        DungeoneeringSkill dungeoneeringSkill = new DungeoneeringSkill();

        SERDES_SKILLS.deserialize(dungeoneeringSkill, data, generics);

        dungeoneeringSkill.setRewardsByName(data.containsKey("rewardsByName") ? data.getAsMap("rewardsByName", String.class, Double.class) : new HashMap<>());
        dungeoneeringSkill.setMythicMobRewards(data.containsKey("mythicMobRewards") ? data.getAsMap("mythicMobRewards", String.class, Double.class) : new HashMap<>());

        return dungeoneeringSkill;
    }
}
