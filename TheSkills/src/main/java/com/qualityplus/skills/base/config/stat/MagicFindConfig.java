package com.qualityplus.skills.base.config.stat;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import com.qualityplus.skills.base.serdes.registry.SerdesSkillsRegistry;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.stats.MagicFindStat;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;


@Configuration(path = "stats/magic_find.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Magic Find Stat      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class MagicFindConfig extends OkaeriConfig {
    public MagicFindStat magicFindStat = MagicFindStat.builder()
                                       .id("magic_find")
                                       .displayName("&b✬ Magic Find")
                                       .description(Arrays.asList("&7With &a%level_number% &7Magic Find you have &a%chance%% &7chance", "&7to find magic objects when killing mobs."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(15)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2VkNTE5OGRkY2RkODliZTkxYzY5ZmU5YWJmZTJjYTRjMTk0N2M3ZTJlYWMxMWYxODQ2YmQzMTIyY2E1YjhjNiJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_intelligence_description%"))
                                               .build())
                                       .chancePerLevel(0.1)
                                       .itemAndChances(ImmutableMap.<XMaterial, Double>builder()
                                               .put(XMaterial.DIAMOND_BLOCK, 2D)
                                               .build())
                                       .baseAmount(0)
                                       .build();
}
