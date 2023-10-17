package com.qualityplus.skills.base.box;

import com.qualityplus.assistant.lib.eu.okaeri.injector.OkaeriInjector;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.skills.api.box.Box;
import com.qualityplus.skills.api.config.ConfigFiles;
import com.qualityplus.skills.api.config.PerkFiles;
import com.qualityplus.skills.api.config.SkillFiles;
import com.qualityplus.skills.api.config.StatFiles;
import com.qualityplus.skills.api.service.ActionBarService;
import com.qualityplus.skills.api.service.SkillsService;
import com.qualityplus.skills.base.config.Commands;
import com.qualityplus.skills.base.config.Config;
import com.qualityplus.skills.base.config.Inventories;
import com.qualityplus.skills.base.config.Messages;
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
import com.qualityplus.skills.base.config.skills.AlchemyConfig;
import com.qualityplus.skills.base.config.skills.CarpentryConfig;
import com.qualityplus.skills.base.config.skills.CombatConfig;
import com.qualityplus.skills.base.config.skills.DiscovererConfig;
import com.qualityplus.skills.base.config.skills.DungeoneeringConfig;
import com.qualityplus.skills.base.config.skills.EnchantingConfig;
import com.qualityplus.skills.base.config.skills.FarmingConfig;
import com.qualityplus.skills.base.config.skills.FishingConfig;
import com.qualityplus.skills.base.config.skills.ForagingConfig;
import com.qualityplus.skills.base.config.skills.MiningConfig;
import com.qualityplus.skills.base.config.skills.RunecraftingConfig;
import com.qualityplus.skills.base.config.skills.TamingConfig;
import com.qualityplus.skills.base.config.stat.CritChanceConfig;
import com.qualityplus.skills.base.config.stat.CritDamageConfig;
import com.qualityplus.skills.base.config.stat.DefenseConfig;
import com.qualityplus.skills.base.config.stat.FerocityConfig;
import com.qualityplus.skills.base.config.stat.IntelligenceConfig;
import com.qualityplus.skills.base.config.stat.MagicFindConfig;
import com.qualityplus.skills.base.config.stat.PetLuckConfig;
import com.qualityplus.skills.base.config.stat.SpeedConfig;
import com.qualityplus.skills.base.config.stat.StrengthConfig;
import com.qualityplus.skills.persistance.SkillsRepository;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;
import java.util.logging.Logger;

/**
 * Utility class for Box impl
 */
@Component
public final class BoxImpl implements Box {
    private @Inject PerkFiles<AbilityDamageConfig, BonusAttackSpeedConfig,
            CactusSkinConfig, EagleEyesConfig, FarmingFortuneConfig,
            ForagingFortuneConfig, IronLungsConfig,
            LeavesMasterConfig,
            LightningPunchConfig, MedicineManConfig,
            MiningFortuneConfig, MiningSpeedConfig,
            OnePunchManConfig, ProjectileMasterConfig,
            RefurbishedConfig,
            SpidermanConfig, SteelSkinConfig,
            WizardConfig, SeaFortuneConfig, PotionMasterConfig, BrewerChanceConfig, OrbMasterConfig> perkFiles;
    private @Inject SkillFiles<AlchemyConfig, CarpentryConfig, CombatConfig, DiscovererConfig, EnchantingConfig, FarmingConfig, FishingConfig, ForagingConfig,
            MiningConfig, RunecraftingConfig, TamingConfig, DungeoneeringConfig> skillFiles;
    private @Inject StatFiles<CritChanceConfig, CritDamageConfig, DefenseConfig, FerocityConfig, IntelligenceConfig, MagicFindConfig,
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
        return this.files;
    }

    @Override
    public SkillFiles<AlchemyConfig, CarpentryConfig, CombatConfig, DiscovererConfig, EnchantingConfig, FarmingConfig, FishingConfig, ForagingConfig,
            MiningConfig, RunecraftingConfig, TamingConfig, DungeoneeringConfig> skillFiles() {
        return this.skillFiles;
    }

    @Override
    public StatFiles<CritChanceConfig, CritDamageConfig, DefenseConfig, FerocityConfig, IntelligenceConfig, MagicFindConfig,
            PetLuckConfig, SpeedConfig, StrengthConfig> statFiles() {
        return this.statFiles;
    }

    @Override
    public PerkFiles<AbilityDamageConfig, BonusAttackSpeedConfig,
            CactusSkinConfig, EagleEyesConfig,
            FarmingFortuneConfig, ForagingFortuneConfig, IronLungsConfig,
            LeavesMasterConfig, LightningPunchConfig,
            MedicineManConfig, MiningFortuneConfig,
            MiningSpeedConfig, OnePunchManConfig,
            ProjectileMasterConfig, RefurbishedConfig,
            SpidermanConfig, SteelSkinConfig,
            WizardConfig, SeaFortuneConfig,
            PotionMasterConfig, BrewerChanceConfig,
            OrbMasterConfig> perkFiles() {
        return this.perkFiles;
    }

    @Override
    public ActionBarService actionBarService() {
        return this.actionBarService;
    }

    @Override
    public SkillsService service() {
        return this.service;
    }

    @Override
    public OkaeriInjector inject() {
        return this.injector;
    }

    @Override
    public Plugin plugin() {
        return this.plugin;
    }

    @Override
    public Logger log() {
        return this.logger;
    }
}
