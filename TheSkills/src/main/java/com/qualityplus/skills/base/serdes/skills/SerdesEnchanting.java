package com.qualityplus.skills.base.serdes.skills;

import com.qualityplus.assistant.util.map.MapUtils;
import com.qualityplus.skills.base.serdes.SerdesSkills;
import com.qualityplus.skills.base.skill.skills.EnchantingSkill;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

import java.util.HashMap;

public final class SerdesEnchanting implements ObjectSerializer<EnchantingSkill> {
    private static final SerdesSkills SERDES_SKILLS = new SerdesSkills();

    @Override
    public boolean supports(@NonNull Class<? super EnchantingSkill> type) {
        return EnchantingSkill.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull EnchantingSkill object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_SKILLS.serialize(object, data, generics);

        data.addAsMap("rewardsPerLevel", MapUtils.check(object.getRewardsPerLevel()), Integer.class, Double.class);
        data.add("rewardForAllEnchantments", object.getRewardForAllEnchantments(), Double.class);
    }

    @Override
    public EnchantingSkill deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        EnchantingSkill enchantingSkill = new EnchantingSkill();

        SERDES_SKILLS.deserialize(enchantingSkill, data, generics);

        enchantingSkill.setRewardsPerLevel(data.containsKey("rewardsPerLevel") ? data.getAsMap("rewardsPerLevel", Integer.class, Double.class) : new HashMap<>());

        enchantingSkill.setRewardForAllEnchantments(data.get("rewardForAllEnchantments", Double.class));

        return enchantingSkill;
    }
}
