package com.qualityplus.skills.base.serdes.perks;

import com.qualityplus.skills.base.perk.perks.AbilityDamagePerk;
import com.qualityplus.skills.base.perk.perks.CactusSkinPerk;
import com.qualityplus.skills.base.serdes.SerdesPerk;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesCactusSkin implements ObjectSerializer<CactusSkinPerk> {
    private static final SerdesPerk SERDES_PERK = new SerdesPerk();

    @Override
    public boolean supports(@NonNull Class<? super CactusSkinPerk> type) {
        return CactusSkinPerk.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull CactusSkinPerk object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_PERK.serialize(object, data, generics);

        data.add("initialAmount", object.getInitialAmount(), Double.class);
        data.add("chancePerLevel", object.getChancePerLevel(), Double.class);
        data.add("damagePerLevel", object.getDamagePerLevel(), Double.class);
        data.add("damageBase", object.getDamageBase(), Double.class);

    }

    @Override
    public CactusSkinPerk deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        CactusSkinPerk alchemySkill = new CactusSkinPerk();

        SERDES_PERK.deserialize(alchemySkill, data, generics);

        alchemySkill.setChancePerLevel(data.get("chancePerLevel", Double.class));
        alchemySkill.setInitialAmount(data.get("initialAmount", Double.class));
        alchemySkill.setDamagePerLevel(data.get("damagePerLevel", Double.class));
        alchemySkill.setDamageBase(data.get("damageBase", Double.class));

        return alchemySkill;
    }
}
