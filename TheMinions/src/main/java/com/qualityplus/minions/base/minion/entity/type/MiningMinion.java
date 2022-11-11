package com.qualityplus.minions.base.minion.entity.type;

import com.qualityplus.minions.base.minion.Minion;
import com.qualityplus.minions.base.minion.entity.ArmorStandMinion;

import java.util.UUID;

public final class MiningMinion extends ArmorStandMinion {
    private MiningMinion(UUID petUniqueId, UUID owner, Minion minion) {
        super(petUniqueId, owner, minion);
    }

    public static MiningMinion create(UUID petUniqueId, UUID owner, Minion minion){
        return new MiningMinion(petUniqueId, owner, minion);
    }



}
