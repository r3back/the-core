package com.qualityplus.skills.base.box;

import com.qualityplus.skills.api.box.Box;
import com.qualityplus.skills.api.config.ConfigFiles;
import com.qualityplus.skills.api.config.PerkFiles;
import com.qualityplus.skills.api.config.SkillFiles;
import com.qualityplus.skills.api.config.StatFiles;
import com.qualityplus.skills.api.service.ActionBarService;
import com.qualityplus.skills.api.service.SkillsService;
import com.qualityplus.skills.base.config.*;
import com.qualityplus.skills.base.config.perk.*;
import com.qualityplus.skills.base.config.skills.*;
import com.qualityplus.skills.base.config.stat.*;
import com.qualityplus.skills.persistance.SkillsRepository;
import eu.okaeri.injector.OkaeriInjector;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

@Component
public final class BoxImpl implements Box {
    private @Inject PerkFiles<AbilityDamageConfig, BonusAttackSpeedConfig, CactusSkinConfig, EagleEyesConfig, FarmingFortuneConfig, ForagingFortuneConfig, IronLungsConfig,
                              LeavesMasterConfig, LightningPunchConfig, MedicineManConfig, MiningFortuneConfig, MiningSpeedConfig, OnePunchManConfig, ProjectileMasterConfig, RefurbishedConfig,
                              SpidermanConfig, SteelSkinConfig, WizardConfig, SeaFortuneConfig, PotionMasterConfig, BrewerChanceConfig, OrbMasterConfig> perkFiles;
    private @Inject SkillFiles<AlchemyConfig, CarpentryConfig, CombatConfig, DiscovererConfig, EnchantingConfig, FarmingConfig, FishingConfig, ForagingConfig,
                               MiningConfig, RunecraftingConfig, TamingConfig, DungeoneeringConfig> skillFiles;
    private @Inject StatFiles<CritChanceConfig, CritDamageConfig, DefenseConfig, FerocityConfig, IntelligenceConfig, MagicFindConfig, MiningSpeedConfig,
                              PetLuckConfig, SpeedConfig, StrengthConfig> statFiles;
    private @Inject ConfigFiles<Config, Inventories, Commands, Messages> files;
    private @Inject ActionBarService actionBarService;
    private @Inject SkillsRepository repository;
    private @Inject SkillsService service;
    private @Inject Plugin plugin;
    private @Inject Logger logger;
    private @Inject("injector") OkaeriInjector injector;


    @Override
    public ConfigFiles<Config, Inventories, Commands, Messages> files() {
        return files;
    }

    @Override
    public SkillFiles<AlchemyConfig, CarpentryConfig, CombatConfig, DiscovererConfig, EnchantingConfig, FarmingConfig, FishingConfig, ForagingConfig,
            MiningConfig, RunecraftingConfig, TamingConfig, DungeoneeringConfig> skillFiles() {
        return skillFiles;
    }

    @Override
    public StatFiles<CritChanceConfig, CritDamageConfig, DefenseConfig, FerocityConfig, IntelligenceConfig, MagicFindConfig, MiningSpeedConfig,
            PetLuckConfig, SpeedConfig, StrengthConfig> statFiles() {
        return statFiles;
    }

    @Override
    public PerkFiles<AbilityDamageConfig, BonusAttackSpeedConfig, CactusSkinConfig, EagleEyesConfig, FarmingFortuneConfig, ForagingFortuneConfig, IronLungsConfig,
            LeavesMasterConfig, LightningPunchConfig, MedicineManConfig, MiningFortuneConfig, MiningSpeedConfig, OnePunchManConfig, ProjectileMasterConfig, RefurbishedConfig,
            SpidermanConfig, SteelSkinConfig, WizardConfig, SeaFortuneConfig, PotionMasterConfig, BrewerChanceConfig, OrbMasterConfig> perkFiles() {
        return perkFiles;
    }

    @Override
    public ActionBarService actionBarService() {
        return actionBarService;
    }

    @Override
    public SkillsService service() {
        return service;
    }

    @Override
    public OkaeriInjector inject() {
        return injector;
    }

    @Override
    public Plugin plugin() {
        return plugin;
    }

    @Override
    public Logger log() {
        return logger;
    }
}
