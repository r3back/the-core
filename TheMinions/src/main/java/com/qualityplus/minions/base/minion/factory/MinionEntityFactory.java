package com.qualityplus.minions.base.minion.factory;

import com.qualityplus.minions.api.minion.entity.MinionEntity;
import com.qualityplus.minions.base.minion.Minion;
import com.qualityplus.minions.base.minion.egg.MinionEgg;
import com.qualityplus.minions.base.minion.entity.type.MiningMinion;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class MinionEntityFactory {
    public MinionEntity create(UUID petUniqueId, UUID owner, Minion pet){
        MinionEgg petEgg = pet.getMinionEgg();

        /*if(petEgg.getPetModelEngine().isUseModelEngine())
            return ModelEnginePet.create(petUniqueId, owner, pet);
        else*/
        return MiningMinion.create(petUniqueId, owner, pet);
    }
}
