package com.qualityplus.skills.base.serdes.skills;

import com.qualityplus.assistant.util.map.MapUtils;
import com.qualityplus.skills.base.serdes.SerdesSkills;
import com.qualityplus.skills.base.skill.skills.CombatSkill;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import org.bukkit.entity.EntityType;

import java.util.HashMap;

public final class SerdesCombat implements ObjectSerializer<CombatSkill> {
    private static final SerdesSkills SERDES_SKILLS = new SerdesSkills();

    @Override
    public boolean supports(@NonNull Class<? super CombatSkill> type) {
        return CombatSkill.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull CombatSkill object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_SKILLS.serialize(object, data, generics);

        data.addAsMap("rewards", MapUtils.check(object.getRewards()), EntityType.class, Double.class);
    }

    @Override
    public CombatSkill deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        CombatSkill combatSkill = new CombatSkill();

        SERDES_SKILLS.deserialize(combatSkill, data, generics);

        combatSkill.setRewards(data.containsKey("rewards") ? data.getAsMap("rewards", EntityType.class, Double.class) : new HashMap<>());

        return combatSkill;
    }
}
