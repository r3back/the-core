package com.qualityplus.skills.base.config.impl;

import com.qualityplus.skills.api.config.StatFiles;
import com.qualityplus.skills.base.config.stat.*;
import eu.okaeri.configs.OkaeriConfig;

import eu.okaeri.platform.core.annotation.Component;

import java.util.Arrays;
import java.util.List;

@Component
public final class StatFilesImpl implements StatFiles<CritChanceConfig, CritDamageConfig, DefenseConfig, FerocityConfig, IntelligenceConfig, MagicFindConfig,
        PetLuckConfig, SpeedConfig, StrengthConfig> {

    private @Inject IntelligenceConfig intelligenceConfig;
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
        reloadAll(critChanceConfig, critDamageConfig, defenseConfig, ferocityConfig, intelligenceConfig, magicFindConfig, petLuckConfig, speedConfig, strengthConfig);
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

    @Override
    public List<OkaeriConfig> getAll() {
        return Arrays.asList(critChanceConfig, critDamageConfig, defenseConfig, ferocityConfig, intelligenceConfig, magicFindConfig, petLuckConfig, speedConfig, strengthConfig);
    }
}
