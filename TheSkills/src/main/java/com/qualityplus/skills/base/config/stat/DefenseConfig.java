package com.qualityplus.skills.base.config.stat;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.serdes.registry.SerdesSkillsRegistry;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.stats.DefenseStat;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;


@Configuration(path = "stats/defense.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Defense Stat      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class DefenseConfig extends OkaeriConfig {
    public DefenseStat defenseStat = DefenseStat.builder()
                                       .id("defense")
                                       .displayName("&a❇ Defense")
                                       .description(Arrays.asList("&7With &a%level_number% &7Defense you reduces the damage", "&7that you take from enemies by &a%percentage%%&7."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(12)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWI1ODcxYzcyOTg3MjY2ZTE1ZjFiZTQ5YjFlYzMzNGVmNmI2MThlOTY1M2ZiNzhlOTE4YWJkMzk1NjNkYmI5MyJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_critic_chance_description%"))
                                               .build())
                                       .damageReductionPercentagePerLevel(0.5)
                                       .baseAmount(0)
                                       .build();
}
