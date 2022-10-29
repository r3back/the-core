package com.qualityplus.skills.base.serdes.perks;

import com.qualityplus.skills.base.perk.perks.OrbMasterPerk;
import com.qualityplus.skills.base.serdes.SerdesPerk;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesOrbMaster implements ObjectSerializer<OrbMasterPerk> {
    private static final SerdesPerk SERDES_PERK = new SerdesPerk();

    @Override
    public boolean supports(@NonNull Class<? super OrbMasterPerk> type) {
        return OrbMasterPerk.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull OrbMasterPerk object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_PERK.serialize(object, data, generics);

        data.add("initialAmount", object.getInitialAmount(), Double.class);
        data.add("chancePerLevel", object.getChancePerLevel(), Double.class);
    }

    @Override
    public OrbMasterPerk deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        OrbMasterPerk alchemySkill = new OrbMasterPerk();

        SERDES_PERK.deserialize(alchemySkill, data, generics);

        alchemySkill.setChancePerLevel(data.get("chancePerLevel", Double.class));
        alchemySkill.setInitialAmount(data.get("initialAmount", Double.class));

        return alchemySkill;
    }
}
