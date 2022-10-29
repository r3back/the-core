package com.qualityplus.skills.base.config.stat;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.serdes.registry.SerdesSkillsRegistry;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.stats.IntelligenceStat;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;


@Configuration(path = "stats/intelligence.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Intelligence Stat      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class IntelligenceConfig extends OkaeriConfig {
    public IntelligenceStat intelligenceStat = IntelligenceStat.builder()
                                       .id("intelligence")
                                       .displayName("&b✎ Intelligence")
                                       .description(Arrays.asList("&7Use your &b%intelligence%✎ Intelligence to show", "&7your weapon powers!"))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(14)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjYyNjUxODc5ZDg3MDQ5OWRhNTBlMzQwMzY4MDBkZGZmZDUyZjNlNGUxOTkzYzVmYzBmYzgyNWQwMzQ0NmQ4YiJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_intelligence_description%"))
                                               .build())
                                       .baseAmount(0)
                                       .build();
}
