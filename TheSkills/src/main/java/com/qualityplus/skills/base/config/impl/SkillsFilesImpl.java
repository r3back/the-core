package com.qualityplus.skills.base.config.impl;

import com.qualityplus.skills.api.config.SkillFiles;
import com.qualityplus.skills.base.config.skills.*;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;

@Component
public final class SkillsFilesImpl implements SkillFiles<AlchemyConfig, CarpentryConfig, CombatConfig, DiscovererConfig, EnchantingConfig, FarmingConfig, FishingConfig,
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
        return alchemyConfig;
    }

    @Override
    public CarpentryConfig carpentry() {
        return carpentryConfig;
    }

    @Override
    public CombatConfig combat() {
        return combatConfig;
    }

    @Override
    public DiscovererConfig discoverer() {
        return discovererConfig;
    }

    @Override
    public DungeoneeringConfig dungeoneering() {
        return dungeoneeringConfig;
    }

    @Override
    public EnchantingConfig enchanting() {
        return enchantingConfig;
    }

    @Override
    public FarmingConfig farming() {
        return farmingConfig;
    }

    @Override
    public FishingConfig fishing() {
        return fishingConfig;
    }

    @Override
    public ForagingConfig foraging() {
        return foragingConfig;
    }

    @Override
    public MiningConfig mining() {
        return miningConfig;
    }

    @Override
    public RunecraftingConfig runeCrafting() {
        return runecraftingConfig;
    }

    @Override
    public TamingConfig taming() {
        return tamingConfig;
    }

    @Override
    public void reloadFiles() {
        reloadAll(alchemyConfig, carpentryConfig, combatConfig, discovererConfig, enchantingConfig, farmingConfig, fishingConfig, foragingConfig, miningConfig, runecraftingConfig, dungeoneeringConfig, tamingConfig);
    }
}
