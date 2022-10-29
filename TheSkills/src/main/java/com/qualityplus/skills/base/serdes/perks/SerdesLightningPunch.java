package com.qualityplus.skills.base.serdes.perks;

import com.qualityplus.skills.base.perk.perks.LightningPunchPerk;
import com.qualityplus.skills.base.serdes.SerdesPerk;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesLightningPunch implements ObjectSerializer<LightningPunchPerk> {
    private static final SerdesPerk SERDES_PERK = new SerdesPerk();

    @Override
    public boolean supports(@NonNull Class<? super LightningPunchPerk> type) {
        return LightningPunchPerk.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull LightningPunchPerk object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_PERK.serialize(object, data, generics);

        data.add("initialAmount", object.getInitialAmount(), Double.class);
        data.add("chancePerLevel", object.getChancePerLevel(), Double.class);
        data.add("damage", object.getDamage(), Double.class);

    }

    @Override
    public LightningPunchPerk deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        LightningPunchPerk alchemySkill = new LightningPunchPerk();

        SERDES_PERK.deserialize(alchemySkill, data, generics);

        alchemySkill.setChancePerLevel(data.get("chancePerLevel", Double.class));
        alchemySkill.setInitialAmount(data.get("initialAmount", Double.class));
        alchemySkill.setDamage(data.get("damage", Double.class));

        return alchemySkill;
    }
}
