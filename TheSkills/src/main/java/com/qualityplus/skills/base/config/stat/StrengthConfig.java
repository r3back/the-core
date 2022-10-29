package com.qualityplus.skills.base.config.stat;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.serdes.registry.SerdesSkillsRegistry;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.stats.StrengthStat;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;


@Configuration(path = "stats/strength.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Strength Stat      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class StrengthConfig extends OkaeriConfig {
    public StrengthStat strengthStat = StrengthStat.builder()
                                       .id("strength")
                                       .displayName("&c❉ Strength")
                                       .description(Collections.singletonList("&a%chance%% &7chance of deal &a+%damage_percentage%% &7more damage"))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(20)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWRiNWNlMGQ0NGMzZTgxMzhkYzJlN2U1MmMyODk3YmI4NzhlMWRiYzIyMGQ3MDY4OWM3YjZiMThkMzE3NWUwZiJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_ferocity_description%"))
                                               .build())
                                       .baseAmount(0)
                                       .build();
}
