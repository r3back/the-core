package com.qualityplus.skills.base.config.impl;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.skills.api.config.SkillFiles;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
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
import java.util.Arrays;
import java.util.List;

/**
 * Utility class foraging skills files
 */
@Component
public final class SkillsFilesImpl implements SkillFiles<AlchemyConfig, CarpentryConfig,
        CombatConfig, DiscovererConfig, EnchantingConfig, FarmingConfig, FishingConfig,
        ForagingConfig, MiningConfig, RunecraftingConfig, TamingConfig, DungeoneeringConfig> {

    private @Inject AlchemyConfig alchemyConfig;
    private @Inject CarpentryConfig carpentryConfig;
    private @Inject CombatConfig combatConfig;
    private @Inject DiscovererConfig discovererConfig;
    private @Inject EnchantingConfig enchantingConfig;
    private @Inject FarmingConfig farmingConfig;
    private @Inject FishingConfig fishingConfig;
    private @Inject ForagingConfig foragingConfig;
    private @Inject MiningConfig miningConfig;
    private @Inject RunecraftingConfig runecraftingConfig;
    private @Inject TamingConfig tamingConfig;
    private @Inject DungeoneeringConfig dungeoneeringConfig;

    @Override
    public AlchemyConfig alchemy() {
        return this.alchemyConfig;
    }

    @Override
    public CarpentryConfig carpentry() {
        return this.carpentryConfig;
    }

    @Override
    public CombatConfig combat() {
        return this.combatConfig;
    }

    @Override
    public DiscovererConfig discoverer() {
        return this.discovererConfig;
    }

    @Override
    public DungeoneeringConfig dungeoneering() {
        return this.dungeoneeringConfig;
    }

    @Override
    public EnchantingConfig enchanting() {
        return this.enchantingConfig;
    }

    @Override
    public FarmingConfig farming() {
        return this.farmingConfig;
    }

    @Override
    public FishingConfig fishing() {
        return this.fishingConfig;
    }

    @Override
    public ForagingConfig foraging() {
        return this.foragingConfig;
    }

    @Override
    public MiningConfig mining() {
        return this.miningConfig;
    }

    @Override
    public RunecraftingConfig runeCrafting() {
        return this.runecraftingConfig;
    }

    @Override
    public TamingConfig taming() {
        return this.tamingConfig;
    }

    @Override
    public List<OkaeriConfig> getAll() {
        return Arrays.asList(this.alchemyConfig, this.carpentryConfig, this
                .combatConfig, this.discovererConfig, this.enchantingConfig, this
                .farmingConfig, this.fishingConfig, this.foragingConfig, this.miningConfig, this
                .runecraftingConfig, this.dungeoneeringConfig, this.tamingConfig);
    }

    @Override
    public void reloadFiles() {
        reloadAll(this.alchemyConfig, this.carpentryConfig, this
                .combatConfig, this.discovererConfig, this
                .enchantingConfig, this.farmingConfig, this
                .fishingConfig, this.foragingConfig, this.miningConfig, this
                .runecraftingConfig, this.dungeoneeringConfig, this.tamingConfig);
    }
}
