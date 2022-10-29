package com.qualityplus.skills.base.serdes.perks;

import com.qualityplus.skills.base.perk.perks.ProjectileMasterPerk;
import com.qualityplus.skills.base.serdes.SerdesPerk;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesProjectileMaster implements ObjectSerializer<ProjectileMasterPerk> {
    private static final SerdesPerk SERDES_PERK = new SerdesPerk();

    @Override
    public boolean supports(@NonNull Class<? super ProjectileMasterPerk> type) {
        return ProjectileMasterPerk.class.isAssignableFrom(type);
    }


    @Override
    public void serialize(@NonNull ProjectileMasterPerk object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_PERK.serialize(object, data, generics);

        data.add("initialAmount", object.getInitialAmount(), Double.class);
        data.add("chancePerLevel", object.getChancePerLevel(), Double.class);
        data.add("extraDamagePercentageBasePerLevel", object.getExtraDamagePercentageBasePerLevel(), Double.class);
        data.add("extraDamagePercentageBase", object.getExtraDamagePercentageBase(), Double.class);

    }

    @Override
    public ProjectileMasterPerk deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        ProjectileMasterPerk alchemySkill = new ProjectileMasterPerk();

        SERDES_PERK.deserialize(alchemySkill, data, generics);

        alchemySkill.setChancePerLevel(data.get("chancePerLevel", Double.class));
        alchemySkill.setInitialAmount(data.get("initialAmount", Double.class));
        alchemySkill.setExtraDamagePercentageBasePerLevel(data.get("extraDamagePercentageBasePerLevel", Double.class));
        alchemySkill.setExtraDamagePercentageBase(data.get("extraDamagePercentageBase", Double.class));

        return alchemySkill;
    }
}
