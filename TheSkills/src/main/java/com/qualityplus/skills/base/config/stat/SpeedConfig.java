package com.qualityplus.skills.base.config.stat;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.serdes.registry.SerdesSkillsRegistry;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.stats.SpeedStat;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;


@Configuration(path = "stats/speed.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Speed Stat      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class SpeedConfig extends OkaeriConfig {
    public SpeedStat speedStat = SpeedStat.builder()
                                       .id("speed")
                                       .displayName("&f✦ Speed")
                                       .description(Arrays.asList("&7With &a%level_number% &7Speed you have &a%extra_speed%% &7of speed increase."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(19)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjA2NzA2ZWVjYjJkNTU4YWNlMjdhYmRhMGIwYjdiODAxZDM2ZDE3ZGQ3YTg5MGE5NTIwZGJlNTIyMzc0ZjhhNiJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_speed_description%"))
                                               .build())
                                       .extraSpeedPercentagePerLevel(1)
                                       .baseAmount(0)
                                       .build();
}
