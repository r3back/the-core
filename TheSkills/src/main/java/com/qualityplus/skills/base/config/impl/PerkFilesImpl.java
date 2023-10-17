package com.qualityplus.skills.base.config.impl;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.skills.api.config.PerkFiles;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import com.qualityplus.skills.base.config.perk.AbilityDamageConfig;
import com.qualityplus.skills.base.config.perk.BonusAttackSpeedConfig;
import com.qualityplus.skills.base.config.perk.BrewerChanceConfig;
import com.qualityplus.skills.base.config.perk.CactusSkinConfig;
import com.qualityplus.skills.base.config.perk.EagleEyesConfig;
import com.qualityplus.skills.base.config.perk.FarmingFortuneConfig;
import com.qualityplus.skills.base.config.perk.ForagingFortuneConfig;
import com.qualityplus.skills.base.config.perk.IronLungsConfig;
import com.qualityplus.skills.base.config.perk.LeavesMasterConfig;
import com.qualityplus.skills.base.config.perk.LightningPunchConfig;
import com.qualityplus.skills.base.config.perk.MedicineManConfig;
import com.qualityplus.skills.base.config.perk.MiningFortuneConfig;
import com.qualityplus.skills.base.config.perk.MiningSpeedConfig;
import com.qualityplus.skills.base.config.perk.OnePunchManConfig;
import com.qualityplus.skills.base.config.perk.OrbMasterConfig;
import com.qualityplus.skills.base.config.perk.PotionMasterConfig;
import com.qualityplus.skills.base.config.perk.ProjectileMasterConfig;
import com.qualityplus.skills.base.config.perk.RefurbishedConfig;
import com.qualityplus.skills.base.config.perk.SeaFortuneConfig;
import com.qualityplus.skills.base.config.perk.SpidermanConfig;
import com.qualityplus.skills.base.config.perk.SteelSkinConfig;
import com.qualityplus.skills.base.config.perk.WizardConfig;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for perk files
 */
@Component
public final class PerkFilesImpl implements PerkFiles<AbilityDamageConfig,
        BonusAttackSpeedConfig, CactusSkinConfig, EagleEyesConfig,
        FarmingFortuneConfig, ForagingFortuneConfig, IronLungsConfig,
        LeavesMasterConfig, LightningPunchConfig, MedicineManConfig,
        MiningFortuneConfig, MiningSpeedConfig, OnePunchManConfig,
        ProjectileMasterConfig, RefurbishedConfig,
        SpidermanConfig, SteelSkinConfig, WizardConfig,
        SeaFortuneConfig, PotionMasterConfig, BrewerChanceConfig, OrbMasterConfig> {

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
        reloadAll(this.abilityDamageConfig, this.bonusAttackSpeedConfig,
                this.cactusSkinConfig, this.eagleEyesConfig,
                this.farmingFortuneConfig, this.foragingFortuneConfig,
                this.ironLungsConfig, this.leavesMasterConfig,
                this.lightningPunchConfig, this.medicineManConfig,
                this.miningFortuneConfig, this.miningSpeedConfig,
                this.onePunchManConfig, this.projectileMasterConfig,
                this.refurbishedConfig, this.spidermanConfig,
                this.steelSkinConfig, this.wizardConfig,
                this.seaLuckyChanceConfig, this.potionMasterConfig,
                this.brewerChanceConfig, this.orbMasterConfig);
    }

    @Override
    public AbilityDamageConfig abilityDamage() {
        return this.abilityDamageConfig;
    }

    @Override
    public BonusAttackSpeedConfig bonusAttack() {
        return this.bonusAttackSpeedConfig;
    }

    @Override
    public CactusSkinConfig cactusSkin() {
        return this.cactusSkinConfig;
    }

    @Override
    public EagleEyesConfig eagleEyes() {
        return this.eagleEyesConfig;
    }

    @Override
    public FarmingFortuneConfig farmingFortune() {
        return this.farmingFortuneConfig;
    }

    @Override
    public ForagingFortuneConfig foragingFortune() {
        return this.foragingFortuneConfig;
    }

    @Override
    public IronLungsConfig ironLungs() {
        return this.ironLungsConfig;
    }

    @Override
    public LeavesMasterConfig leavesMaster() {
        return this.leavesMasterConfig;
    }

    @Override
    public LightningPunchConfig lightningPunch() {
        return this.lightningPunchConfig;
    }

    @Override
    public MedicineManConfig medicineMan() {
        return this.medicineManConfig;
    }

    @Override
    public MiningFortuneConfig miningFortune() {
        return this.miningFortuneConfig;
    }

    @Override
    public MiningSpeedConfig miningSpeed() {
        return this.miningSpeedConfig;
    }

    @Override
    public OnePunchManConfig onePunchMan() {
        return this.onePunchManConfig;
    }

    @Override
    public ProjectileMasterConfig projectileMaster() {
        return this.projectileMasterConfig;
    }

    @Override
    public RefurbishedConfig refurbished() {
        return this.refurbishedConfig;
    }

    @Override
    public SpidermanConfig spiderman() {
        return this.spidermanConfig;
    }

    @Override
    public SteelSkinConfig steelSkin() {
        return this.steelSkinConfig;
    }

    @Override
    public WizardConfig wizard() {
        return this.wizardConfig;
    }

    @Override
    public SeaFortuneConfig seaFortune() {
        return this.seaLuckyChanceConfig;
    }

    @Override
    public PotionMasterConfig potionMaster() {
        return this.potionMasterConfig;
    }

    @Override
    public BrewerChanceConfig brewChance() {
        return this.brewerChanceConfig;
    }

    @Override
    public OrbMasterConfig orbMaster() {
        return this.orbMasterConfig;
    }

    @Override
    public List<OkaeriConfig> getAll() {
        return Arrays.asList(this.abilityDamageConfig, this.bonusAttackSpeedConfig,
                this.cactusSkinConfig, this.eagleEyesConfig, this.farmingFortuneConfig,
                this.foragingFortuneConfig, this.ironLungsConfig, this.leavesMasterConfig,
                this.lightningPunchConfig, this.medicineManConfig,
                this.miningFortuneConfig, this.miningSpeedConfig,
                this.onePunchManConfig, this.projectileMasterConfig,
                this.refurbishedConfig, this.spidermanConfig,
                this.steelSkinConfig, this.wizardConfig,
                this.seaLuckyChanceConfig, this.potionMasterConfig,
                this.brewerChanceConfig, this.orbMasterConfig);
    }
}
