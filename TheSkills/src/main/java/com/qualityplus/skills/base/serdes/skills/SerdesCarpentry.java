package com.qualityplus.skills.base.serdes.skills;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.map.MapUtils;
import com.qualityplus.skills.base.serdes.SerdesSkills;
import com.qualityplus.skills.base.skill.skills.CarpentrySkill;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

import java.util.HashMap;

public final class SerdesCarpentry implements ObjectSerializer<CarpentrySkill> {
    private static final SerdesSkills SERDES_SKILLS = new SerdesSkills();

    @Override
    public boolean supports(@NonNull Class<? super CarpentrySkill> type) {
        return CarpentrySkill.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull CarpentrySkill object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_SKILLS.serialize(object, data, generics);
        data.addAsMap("rewards", MapUtils.check(object.getRewards()), XMaterial.class, Double.class);
        data.add("xpForAllItems", object.getXpForAllItems(), Double.class);
    }

    @Override
    public CarpentrySkill deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        CarpentrySkill carpentrySkill = new CarpentrySkill();

        SERDES_SKILLS.deserialize(carpentrySkill, data, generics);

        carpentrySkill.setRewards(data.containsKey("rewards") ? data.getAsMap("rewards", XMaterial.class, Double.class) : new HashMap<>());

        carpentrySkill.setXpForAllItems(data.get("xpForAllItems", Double.class));

        return carpentrySkill;
    }
}
