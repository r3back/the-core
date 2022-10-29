package com.qualityplus.skills.base.serdes.perks;

import com.qualityplus.skills.base.perk.perks.OnePunchManPerk;
import com.qualityplus.skills.base.serdes.SerdesPerk;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesOnePunchMan implements ObjectSerializer<OnePunchManPerk> {
    private static final SerdesPerk SERDES_PERK = new SerdesPerk();

    @Override
    public boolean supports(@NonNull Class<? super OnePunchManPerk> type) {
        return OnePunchManPerk.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull OnePunchManPerk object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_PERK.serialize(object, data, generics);

        data.add("initialAmount", object.getInitialAmount(), Double.class);
        data.add("chancePerLevel", object.getChancePerLevel(), Double.class);
        data.add("canBeUsedWithPlayers", object.isCanBeUsedWithPlayers(), Boolean.class);

    }

    @Override
    public OnePunchManPerk deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        OnePunchManPerk alchemySkill = new OnePunchManPerk();

        SERDES_PERK.deserialize(alchemySkill, data, generics);

        alchemySkill.setChancePerLevel(data.get("chancePerLevel", Double.class));
        alchemySkill.setInitialAmount(data.get("initialAmount", Double.class));
        alchemySkill.setCanBeUsedWithPlayers(data.get("canBeUsedWithPlayers", Boolean.class));

        return alchemySkill;
    }
}
