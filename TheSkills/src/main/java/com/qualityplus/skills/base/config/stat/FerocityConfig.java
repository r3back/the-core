package com.qualityplus.skills.base.config.stat;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.serdes.registry.SerdesSkillsRegistry;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.stats.FerocityStat;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;


@Configuration(path = "stats/ferocity.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Ferocity Stat      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class FerocityConfig extends OkaeriConfig {
    public FerocityStat ferocityStat = FerocityStat.builder()
                                       .id("ferocity")
                                       .displayName("&c⫽ Ferocity")
                                       .description(Arrays.asList("&a%chance%% chance to deal same damage", "&7twice."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(13)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWEwNmMxY2ZiMDliM2ZkMzMzOTFmZTM4ZjliZDU4Yjg4NDQyNjQxZTM0OTIwNzJhMWZmYWExZjliYmY4MmJhNCJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_ferocity_description%"))
                                               .build())
                                       .chancePerLevel(4)
                                       .baseAmount(0)
                                       .build();
}
