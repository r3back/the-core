package com.qualityplus.skills.base.serdes.perks;

import com.qualityplus.skills.base.perk.perks.MedicineManPerk;
import com.qualityplus.skills.base.serdes.SerdesPerk;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesMedicineMan implements ObjectSerializer<MedicineManPerk> {
    private static final SerdesPerk SERDES_PERK = new SerdesPerk();

    @Override
    public boolean supports(@NonNull Class<? super MedicineManPerk> type) {
        return MedicineManPerk.class.isAssignableFrom(type);
    }


    @Override
    public void serialize(@NonNull MedicineManPerk object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_PERK.serialize(object, data, generics);

        data.add("initialAmount", object.getInitialAmount(), Double.class);
        data.add("chancePerLevel", object.getChancePerLevel(), Double.class);
        data.add("healPercentageToRegenerateBase", object.getHealPercentageToRegenerateBase(), Double.class);
        data.add("healPercentageToRegeneratePerLevel", object.getHealPercentageToRegeneratePerLevel(), Double.class);

    }

    @Override
    public MedicineManPerk deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        MedicineManPerk alchemySkill = new MedicineManPerk();

        SERDES_PERK.deserialize(alchemySkill, data, generics);

        alchemySkill.setChancePerLevel(data.get("chancePerLevel", Double.class));
        alchemySkill.setInitialAmount(data.get("initialAmount", Double.class));
        alchemySkill.setHealPercentageToRegenerateBase(data.get("healPercentageToRegenerateBase", Double.class));
        alchemySkill.setHealPercentageToRegeneratePerLevel(data.get("healPercentageToRegeneratePerLevel", Double.class));

        return alchemySkill;
    }
}
