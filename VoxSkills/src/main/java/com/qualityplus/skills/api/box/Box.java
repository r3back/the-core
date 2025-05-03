package com.qualityplus.skills.api.box;

import com.qualityplus.assistant.lib.eu.okaeri.injector.OkaeriInjector;
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
import com.qualityplus.skills.base.config.perk.*;
import com.qualityplus.skills.base.config.skills.*;
import com.qualityplus.skills.base.config.stat.*;
import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

public interface Box {
    ConfigFiles<Config, Inventories, Commands, Messages> files();
    SkillFiles<AlchemyConfig, CarpentryConfig, CombatConfig, DiscovererConfig, EnchantingConfig, FarmingConfig, FishingConfig,
            ForagingConfig, MiningConfig, RunecraftingConfig, TamingConfig, DungeoneeringConfig> skillFiles();
    StatFiles<CritChanceConfig, CritDamageConfig, DefenseConfig, FerocityConfig, IntelligenceConfig, MagicFindConfig, PetLuckConfig, SpeedConfig, StrengthConfig, MaxHealthConfig> statFiles();
    PerkFiles<AbilityDamageConfig, BonusAttackSpeedConfig, CactusSkinConfig, EagleEyesConfig, FarmingFortuneConfig, ForagingFortuneConfig, IronLungsConfig,
            LeavesMasterConfig, LightningPunchConfig, MedicineManConfig, MiningFortuneConfig, MiningSpeedConfig, OnePunchManConfig, ProjectileMasterConfig,
            RefurbishedConfig, SpidermanConfig, SteelSkinConfig, WizardConfig, SeaFortuneConfig, PotionMasterConfig, BrewerChanceConfig, OrbMasterConfig> perkFiles();
    ActionBarService actionBarService();
    SkillsService service();
    OkaeriInjector inject();
    Plugin plugin();
    Logger log();
}
