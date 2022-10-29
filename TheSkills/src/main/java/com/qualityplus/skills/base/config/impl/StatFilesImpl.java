package com.qualityplus.skills.base.config.impl;

import com.qualityplus.skills.api.config.StatFiles;
import com.qualityplus.skills.base.config.perk.MiningSpeedConfig;
import com.qualityplus.skills.base.config.stat.*;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;

@Component
public final class StatFilesImpl implements StatFiles<CritChanceConfig, CritDamageConfig, DefenseConfig, FerocityConfig, IntelligenceConfig, MagicFindConfig, MiningSpeedConfig,
        PetLuckConfig, SpeedConfig, StrengthConfig> {

    private @Inject IntelligenceConfig intelligenceConfig;
    private @Inject MiningSpeedConfig miningSpeedConfig;
    private @Inject CritChanceConfig critChanceConfig;
    private @Inject CritDamageConfig critDamageConfig;
    private @Inject MagicFindConfig magicFindConfig;
    private @Inject FerocityConfig ferocityConfig;
    private @Inject StrengthConfig strengthConfig;
    private @Inject DefenseConfig defenseConfig;
    private @Inject PetLuckConfig petLuckConfig;
    private @Inject SpeedConfig speedConfig;

    @Override
    public void reloadFiles() {
        reloadAll(critChanceConfig, critDamageConfig, defenseConfig, ferocityConfig, intelligenceConfig, magicFindConfig, miningSpeedConfig, petLuckConfig, speedConfig, strengthConfig);
    }

    @Override
    public CritChanceConfig critChance() {
        return critChanceConfig;
    }

    @Override
    public CritDamageConfig critDamage() {
        return critDamageConfig;
    }

    @Override
    public DefenseConfig defense() {
        return defenseConfig;
    }

    @Override
    public FerocityConfig ferocity() {
        return ferocityConfig;
    }

    @Override
    public IntelligenceConfig intelligence() {
        return intelligenceConfig;
    }

    @Override
    public MagicFindConfig magicFind() {
        return magicFindConfig;
    }

    @Override
    public MiningSpeedConfig mining() {
        return miningSpeedConfig;
    }

    @Override
    public PetLuckConfig petLuck() {
        return petLuckConfig;
    }

    @Override
    public SpeedConfig speed() {
        return speedConfig;
    }

    @Override
    public StrengthConfig strength() {
        return strengthConfig;
    }
}
