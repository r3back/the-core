package com.qualityplus.skills.base.config.impl;

import com.qualityplus.skills.api.config.PerkFiles;
import com.qualityplus.skills.base.config.perk.OrbMasterConfig;
import com.qualityplus.skills.base.config.perk.*;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;

@Component
public final class PerkFilesImpl implements PerkFiles<AbilityDamageConfig, BonusAttackSpeedConfig, CactusSkinConfig, EagleEyesConfig, FarmingFortuneConfig, ForagingFortuneConfig, IronLungsConfig,
        LeavesMasterConfig, LightningPunchConfig, MedicineManConfig, MiningFortuneConfig, MiningSpeedConfig, OnePunchManConfig, ProjectileMasterConfig, RefurbishedConfig,
        SpidermanConfig, SteelSkinConfig, WizardConfig, SeaFortuneConfig, PotionMasterConfig, BrewerChanceConfig, OrbMasterConfig> {

    private @Inject AbilityDamageConfig abilityDamageConfig;
    private @Inject BonusAttackSpeedConfig bonusAttackSpeedConfig;
    private @Inject CactusSkinConfig cactusSkinConfig;
    private @Inject EagleEyesConfig eagleEyesConfig;
    private @Inject FarmingFortuneConfig farmingFortuneConfig;
    private @Inject ForagingFortuneConfig foragingFortuneConfig;
    private @Inject IronLungsConfig ironLungsConfig;
    private @Inject LeavesMasterConfig leavesMasterConfig;
    private @Inject LightningPunchConfig lightningPunchConfig;
    private @Inject MedicineManConfig medicineManConfig;
    private @Inject MiningFortuneConfig miningFortuneConfig;
    private @Inject MiningSpeedConfig miningSpeedConfig;
    private @Inject OnePunchManConfig onePunchManConfig;
    private @Inject ProjectileMasterConfig projectileMasterConfig;
    private @Inject RefurbishedConfig refurbishedConfig;
    private @Inject SpidermanConfig spidermanConfig;
    private @Inject SteelSkinConfig steelSkinConfig;
    private @Inject WizardConfig wizardConfig;
    private @Inject SeaFortuneConfig seaLuckyChanceConfig;
    private @Inject PotionMasterConfig potionMasterConfig;
    private @Inject BrewerChanceConfig brewerChanceConfig;
    private @Inject OrbMasterConfig orbMasterConfig;

    @Override
    public void reloadFiles() {
        reloadAll(abilityDamageConfig, bonusAttackSpeedConfig, cactusSkinConfig, eagleEyesConfig, farmingFortuneConfig, foragingFortuneConfig, ironLungsConfig, leavesMasterConfig,
                  lightningPunchConfig, medicineManConfig, miningFortuneConfig, miningSpeedConfig, onePunchManConfig, projectileMasterConfig, refurbishedConfig, spidermanConfig,
                  steelSkinConfig, wizardConfig, seaLuckyChanceConfig, potionMasterConfig, brewerChanceConfig, orbMasterConfig);
    }

    @Override
    public AbilityDamageConfig abilityDamage() {
        return abilityDamageConfig;
    }

    @Override
    public BonusAttackSpeedConfig bonusAttack() {
        return bonusAttackSpeedConfig;
    }

    @Override
    public CactusSkinConfig cactusSkin() {
        return cactusSkinConfig;
    }

    @Override
    public EagleEyesConfig eagleEyes() {
        return eagleEyesConfig;
    }

    @Override
    public FarmingFortuneConfig farmingFortune() {
        return farmingFortuneConfig;
    }

    @Override
    public ForagingFortuneConfig foragingFortune() {
        return foragingFortuneConfig;
    }

    @Override
    public IronLungsConfig ironLungs() {
        return ironLungsConfig;
    }

    @Override
    public LeavesMasterConfig leavesMaster() {
        return leavesMasterConfig;
    }

    @Override
    public LightningPunchConfig lightningPunch() {
        return lightningPunchConfig;
    }

    @Override
    public MedicineManConfig medicineMan() {
        return medicineManConfig;
    }

    @Override
    public MiningFortuneConfig miningFortune() {
        return miningFortuneConfig;
    }

    @Override
    public MiningSpeedConfig miningSpeed() {
        return miningSpeedConfig;
    }

    @Override
    public OnePunchManConfig onePunchMan() {
        return onePunchManConfig;
    }

    @Override
    public ProjectileMasterConfig projectileMaster() {
        return projectileMasterConfig;
    }

    @Override
    public RefurbishedConfig refurbished() {
        return refurbishedConfig;
    }

    @Override
    public SpidermanConfig spiderman() {
        return spidermanConfig;
    }

    @Override
    public SteelSkinConfig steelSkin() {
        return steelSkinConfig;
    }

    @Override
    public WizardConfig wizard() {
        return wizardConfig;
    }

    @Override
    public SeaFortuneConfig seaFortune() {
        return seaLuckyChanceConfig;
    }

    @Override
    public PotionMasterConfig potionMaster() {
        return potionMasterConfig;
    }

    @Override
    public BrewerChanceConfig brewChance() {
        return brewerChanceConfig;
    }

    @Override
    public OrbMasterConfig orbMaster() {
        return orbMasterConfig;
    }
}
