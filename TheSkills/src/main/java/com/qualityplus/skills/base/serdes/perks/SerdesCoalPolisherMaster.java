package com.qualityplus.skills.base.serdes.perks;

import com.qualityplus.skills.base.perk.perks.CoalPolisherMasterPerk;
import com.qualityplus.skills.base.serdes.SerdesPerk;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesCoalPolisherMaster implements ObjectSerializer<CoalPolisherMasterPerk> {
    private static final SerdesPerk SERDES_PERK = new SerdesPerk();

    @Override
    public boolean supports(@NonNull Class<? super CoalPolisherMasterPerk> type) {
        return CoalPolisherMasterPerk.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull CoalPolisherMasterPerk object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_PERK.serialize(object, data, generics);

        data.add("initialAmount", object.getInitialAmount(), Double.class);
        data.add("chancePerLevel", object.getChancePerLevel(), Double.class);
    }

    @Override
    public CoalPolisherMasterPerk deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        CoalPolisherMasterPerk alchemySkill = new CoalPolisherMasterPerk();

        SERDES_PERK.deserialize(alchemySkill, data, generics);

        alchemySkill.setChancePerLevel(data.get("chancePerLevel", Double.class));
        alchemySkill.setInitialAmount(data.get("initialAmount", Double.class));

        return alchemySkill;
    }
}
