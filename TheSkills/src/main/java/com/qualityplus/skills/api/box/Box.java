package com.qualityplus.skills.api.box;

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
import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

public interface Box {
    ConfigFiles<Config, Inventories, Commands, Messages> files();
    SkillFiles<AlchemyConfig, CarpentryConfig, CombatConfig, DiscovererConfig, EnchantingConfig, FarmingConfig, FishingConfig,
            ForagingConfig, MiningConfig, RunecraftingConfig, TamingConfig, DungeoneeringConfig> skillFiles();
    StatFiles<CritChanceConfig, CritDamageConfig, DefenseConfig, FerocityConfig, IntelligenceConfig, MagicFindConfig, MiningSpeedConfig,
            PetLuckConfig, SpeedConfig, StrengthConfig> statFiles();
    PerkFiles<AbilityDamageConfig, BonusAttackSpeedConfig, CactusSkinConfig, EagleEyesConfig, FarmingFortuneConfig, ForagingFortuneConfig, IronLungsConfig,
            LeavesMasterConfig, LightningPunchConfig, MedicineManConfig, MiningFortuneConfig, MiningSpeedConfig, OnePunchManConfig, ProjectileMasterConfig,
            RefurbishedConfig, SpidermanConfig, SteelSkinConfig, WizardConfig, SeaFortuneConfig, PotionMasterConfig, BrewerChanceConfig, OrbMasterConfig> perkFiles();
    ActionBarService actionBarService();
    SkillsService service();
    OkaeriInjector inject();
    Plugin plugin();
    Logger log();
}
