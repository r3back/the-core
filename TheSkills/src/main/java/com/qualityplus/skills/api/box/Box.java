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
import org.bukkit.plugin.Plugin;
import java.util.logging.Logger;

/**
 * Utility interface for box
 */
public interface Box {
    /**
     * Adds a config files
     *
     * @return {@link ConfigFiles}
     */
    public ConfigFiles<Config, Inventories, Commands, Messages> files();

    /**
     * Adds a skill files
     *
     * @return {@link SkillFiles}
     */
    public SkillFiles<AlchemyConfig, CarpentryConfig, CombatConfig, DiscovererConfig, EnchantingConfig, FarmingConfig, FishingConfig,
            ForagingConfig, MiningConfig, RunecraftingConfig, TamingConfig, DungeoneeringConfig> skillFiles();

    /**
     * Adds a stat files
     *
     * @return {@link StatFiles}
     */
    public StatFiles<CritChanceConfig, CritDamageConfig, DefenseConfig, FerocityConfig,
            IntelligenceConfig, MagicFindConfig, PetLuckConfig, SpeedConfig, StrengthConfig> statFiles();

    /**
     * Adds a perk files
     *
     * @return {@link PerkFiles}
     */
    public PerkFiles<AbilityDamageConfig, BonusAttackSpeedConfig,
            CactusSkinConfig, EagleEyesConfig,
            FarmingFortuneConfig, ForagingFortuneConfig, IronLungsConfig,
            LeavesMasterConfig, LightningPunchConfig, MedicineManConfig,
            MiningFortuneConfig, MiningSpeedConfig,
            OnePunchManConfig, ProjectileMasterConfig,
            RefurbishedConfig, SpidermanConfig, SteelSkinConfig,
            WizardConfig, SeaFortuneConfig,
            PotionMasterConfig, BrewerChanceConfig, OrbMasterConfig> perkFiles();

    /**
     * Adds an action bar service
     *
     * @return {@link ActionBarService}
     */
    public ActionBarService actionBarService();

    /**
     * Adds a service
     *
     * @return {@link SkillsService}
     */
    public SkillsService service();

    /**
     * Adds an inject
     *
      * @return {@link OkaeriInjector}
     */
    public OkaeriInjector inject();

    /**
     * Adds a plugin
     *
     * @return {@link Plugin}
     */
    public Plugin plugin();

    /**
     * Adds a log
     *
     * @return {@link Logger}
     */
    public Logger log();
}
