package com.qualityplus.skills.base.serdes.perks;

import com.qualityplus.skills.base.perk.perks.LeavesMasterPerk;
import com.qualityplus.skills.base.serdes.SerdesPerk;
import com.qualityplus.skills.base.serdes.perks.common.SerdesRandomBlockDropPerk;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesLeavesMaster implements ObjectSerializer<LeavesMasterPerk> {
    private static final SerdesPerk SERDES_PERK = new SerdesPerk();
    private static final SerdesRandomBlockDropPerk SERDES_RANDOM_BLOCK_DROP_PERK = new SerdesRandomBlockDropPerk();

    @Override
    public boolean supports(@NonNull Class<? super LeavesMasterPerk> type) {
        return LeavesMasterPerk.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull LeavesMasterPerk object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_PERK.serialize(object, data, generics);
        SERDES_RANDOM_BLOCK_DROP_PERK.serialize(object, data, generics);

        data.add("initialAmount", object.getInitialAmount(), Double.class);
        data.add("chancePerLevel", object.getChancePerLevel(), Double.class);
    }

    @Override
    public LeavesMasterPerk deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        LeavesMasterPerk alchemySkill = new LeavesMasterPerk();

        SERDES_PERK.deserialize(alchemySkill, data, generics);
        SERDES_RANDOM_BLOCK_DROP_PERK.deserialize(alchemySkill, data, generics);

        alchemySkill.setChancePerLevel(data.get("chancePerLevel", Double.class));
        alchemySkill.setInitialAmount(data.get("initialAmount", Double.class));

        return alchemySkill;
    }
}
