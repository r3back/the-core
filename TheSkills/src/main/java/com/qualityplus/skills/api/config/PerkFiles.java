package com.qualityplus.skills.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;

public interface PerkFiles<AD, BA, CS, EE, FAF, FOF, IL, LM, LP, MM, MF, MS, OP, PM, RF, SP, SK, W, SF, PTM, BC, OM> extends ConfigReloader {
    AD abilityDamage();
    BA bonusAttack();
    CS cactusSkin();
    EE eagleEyes();
    FAF farmingFortune();
    FOF foragingFortune();
    IL ironLungs();
    LM leavesMaster();
    LP lightningPunch();
    MM medicineMan();
    MF miningFortune();
    MS miningSpeed();
    OP onePunchMan();
    PM projectileMaster();
    RF refurbished();
    SP spiderman();
    SK steelSkin();
    W wizard();
    SF seaFortune();
    PTM potionMaster();
    BC brewChance();
    OM orbMaster();
}
