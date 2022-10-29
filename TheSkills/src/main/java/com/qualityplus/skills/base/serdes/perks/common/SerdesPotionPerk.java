package com.qualityplus.skills.base.serdes.perks.common;

import com.qualityplus.skills.base.perk.perks.common.AbstractPotionPerk;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesPotionPerk implements ObjectSerializer<AbstractPotionPerk> {

    @Override
    public boolean supports(@NonNull Class<? super AbstractPotionPerk> type) {
        return AbstractPotionPerk.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull AbstractPotionPerk object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("secondsDurationPerLevel", object.getSecondsDurationPerLevel());
        data.add("baseSecondsDuration", object.getBaseSecondsDuration());
        data.add("level", object.getLevel());
    }

    @Override
    public AbstractPotionPerk deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        return null;
    }

    public void deserialize(AbstractPotionPerk skill, @NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        skill.setSecondsDurationPerLevel(data.get("secondsDurationPerLevel", Integer.class));
        skill.setBaseSecondsDuration(data.get("baseSecondsDuration", Integer.class));
        skill.setLevel(data.get("level", Integer.class));
    }

}
