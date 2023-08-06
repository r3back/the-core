package com.qualityplus.skills.base.config.impl;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.skills.api.config.StatFiles;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import com.qualityplus.skills.base.config.stat.CritChanceConfig;
import com.qualityplus.skills.base.config.stat.CritDamageConfig;
import com.qualityplus.skills.base.config.stat.DefenseConfig;
import com.qualityplus.skills.base.config.stat.FerocityConfig;
import com.qualityplus.skills.base.config.stat.IntelligenceConfig;
import com.qualityplus.skills.base.config.stat.MagicFindConfig;
import com.qualityplus.skills.base.config.stat.PetLuckConfig;
import com.qualityplus.skills.base.config.stat.SpeedConfig;
import com.qualityplus.skills.base.config.stat.StrengthConfig;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for stats files
 */
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
        reloadAll(this.critChanceConfig, this.critDamageConfig, this.defenseConfig,
                this.ferocityConfig, this.intelligenceConfig, this.magicFindConfig,
                this.petLuckConfig, this.speedConfig, this.strengthConfig);
    }

    @Override
    public CritChanceConfig critChance() {
        return this.critChanceConfig;
    }

    @Override
    public CritDamageConfig critDamage() {
        return this.critDamageConfig;
    }

    @Override
    public DefenseConfig defense() {
        return this.defenseConfig;
    }

    @Override
    public FerocityConfig ferocity() {
        return this.ferocityConfig;
    }

    @Override
    public IntelligenceConfig intelligence() {
        return this.intelligenceConfig;
    }

    @Override
    public MagicFindConfig magicFind() {
        return this.magicFindConfig;
    }


    @Override
    public PetLuckConfig petLuck() {
        return this.petLuckConfig;
    }

    @Override
    public SpeedConfig speed() {
        return this.speedConfig;
    }

    @Override
    public StrengthConfig strength() {
        return this.strengthConfig;
    }

    @Override
    public List<OkaeriConfig> getAll() {
        return Arrays.asList(this.critChanceConfig, this.critDamageConfig, this.defenseConfig,
                this.ferocityConfig, this.intelligenceConfig, this.magicFindConfig,
                this.petLuckConfig, this.speedConfig, this.strengthConfig);
    }
}
