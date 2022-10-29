package com.qualityplus.skills.base.serdes.skills;

import com.qualityplus.assistant.util.map.MapUtils;
import com.qualityplus.skills.base.serdes.SerdesSkills;
import com.qualityplus.skills.base.skill.skills.DiscovererSkill;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

import java.util.HashMap;

public final class SerdesDiscoverer implements ObjectSerializer<DiscovererSkill> {
    private static final SerdesSkills SERDES_SKILLS = new SerdesSkills();

    @Override
    public boolean supports(@NonNull Class<? super DiscovererSkill> type) {
        return DiscovererSkill.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull DiscovererSkill object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_SKILLS.serialize(object, data, generics);

        data.addAsMap("rewardsPerBlocksWalked", MapUtils.check(object.getRewardsPerBlocksWalked()), Integer.class, Double.class);
    }

    @Override
    public DiscovererSkill deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        DiscovererSkill adventurerSkill = new DiscovererSkill();

        SERDES_SKILLS.deserialize(adventurerSkill, data, generics);

        adventurerSkill.setRewardsPerBlocksWalked(data.containsKey("rewardsPerBlocksWalked") ? data.getAsMap("rewardsPerBlocksWalked", Integer.class, Double.class) : new HashMap<>());

        return adventurerSkill;
    }
}
