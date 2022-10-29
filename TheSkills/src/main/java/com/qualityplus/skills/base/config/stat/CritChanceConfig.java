package com.qualityplus.skills.base.config.stat;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.serdes.registry.SerdesSkillsRegistry;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.stats.CritChanceStat;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;


@Configuration(path = "stats/critic_chance.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Critic Chance Stat      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class CritChanceConfig extends OkaeriConfig {
    public CritChanceStat critChanceStat = CritChanceStat.builder()
                                       .id("critic_chance")
                                       .displayName("&9☣ Critic Chance")
                                       .description(Arrays.asList("&a%chance%% &7chance to deal extra damage", "&7on enemies."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(10)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U0ZjQ5NTM1YTI3NmFhY2M0ZGM4NDEzM2JmZTgxYmU1ZjJhNDc5OWE0YzA0ZDlhNGRkYjcyZDgxOWVjMmIyYiJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_critic_chance_description%"))
                                               .build())
                                       .chancePerLevel(2)
                                       .baseAmount(0)
                                       .build();
}
