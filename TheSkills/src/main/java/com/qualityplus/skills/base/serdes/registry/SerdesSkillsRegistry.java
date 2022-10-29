package com.qualityplus.skills.base.serdes.registry;

import com.qualityplus.skills.base.serdes.perks.*;
import com.qualityplus.skills.base.serdes.skills.*;
import com.qualityplus.skills.base.serdes.stats.*;
import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import eu.okaeri.configs.serdes.SerdesRegistry;
import lombok.NonNull;

public final class SerdesSkillsRegistry implements OkaeriSerdesPack {
    @Override
    public void register(@NonNull SerdesRegistry registry) {
        //Skills
        registry.register(new SerdesAlchemy());
        registry.register(new SerdesCarpentry());
        registry.register(new SerdesCombat());
        registry.register(new SerdesDiscoverer());
        registry.register(new SerdesEnchanting());
        registry.register(new SerdesFarming());
        registry.register(new SerdesFishing());
        registry.register(new SerdesForaging());
        registry.register(new SerdesMining());
        registry.register(new SerdesRunecrafting());
        registry.register(new SerdesTaming());
        registry.register(new SerdesDuneoneering());
        //Stats
        registry.register(new SerdesBrewChance());
        registry.register(new SerdesCritChance());
        registry.register(new SerdesCritDamage());
        registry.register(new SerdesDefense());
        registry.register(new SerdesFerocity());
        registry.register(new SerdesIntelligence());
        registry.register(new SerdesMagicFind());
        registry.register(new SerdesPetLuck());
        registry.register(new SerdesSpeed());
        registry.register(new SerdesStrength());

        //Perks
        registry.register(new SerdesAbilityDamage());
        registry.register(new SerdesBonusAttackSpeed());
        registry.register(new SerdesCactusSkin());
        registry.register(new SerdesCoalPolisherMaster());
        registry.register(new SerdesEagleEyes());
        registry.register(new SerdesFarmingFortune());
        registry.register(new SerdesForagingFortune());
        registry.register(new SerdesIronLungs());
        registry.register(new SerdesLeavesMaster());
        registry.register(new SerdesLightningPunch());
        registry.register(new SerdesMedicineMan());
        registry.register(new SerdesMiningFortune());
        registry.register(new SerdesMiningSpeed());
        registry.register(new SerdesOnePunchMan());
        registry.register(new SerdesProjectileMaster());
        registry.register(new SerdesRefurbished());
        registry.register(new SerdesSpiderman());
        registry.register(new SerdesSteelSkin());
        registry.register(new SerdesWizard());
        registry.register(new SerdesSeaFortune());
        registry.register(new SerdesPotionMaster());
        registry.register(new SerdesOrbMaster());

    }
}
