package com.qualityplus.skills.base.serdes.perks;

import com.qualityplus.skills.base.perk.perks.AbilityDamagePerk;
import com.qualityplus.skills.base.perk.perks.BonusAttackSpeedPerk;
import com.qualityplus.skills.base.serdes.SerdesPerk;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesBonusAttackSpeed implements ObjectSerializer<BonusAttackSpeedPerk> {
    private static final SerdesPerk SERDES_PERK = new SerdesPerk();

    @Override
    public boolean supports(@NonNull Class<? super BonusAttackSpeedPerk> type) {
        return BonusAttackSpeedPerk.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull BonusAttackSpeedPerk object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_PERK.serialize(object, data, generics);

        data.add("initialAmount", object.getInitialAmount(), Double.class);
        data.add("chancePerLevel", object.getChancePerLevel(), Double.class);
    }

    @Override
    public BonusAttackSpeedPerk deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        BonusAttackSpeedPerk alchemySkill = new BonusAttackSpeedPerk();

        SERDES_PERK.deserialize(alchemySkill, data, generics);

        alchemySkill.setChancePerLevel(data.get("chancePerLevel", Double.class));
        alchemySkill.setInitialAmount(data.get("initialAmount", Double.class));

        return alchemySkill;
    }
}
