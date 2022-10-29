package com.qualityplus.skills.base.config.stat;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.serdes.registry.SerdesSkillsRegistry;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.stats.CritChanceStat;
import com.qualityplus.skills.base.stat.stats.CritDamageStat;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;


@Configuration(path = "stats/critic_damage.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Critic Damage Stat      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class CritDamageConfig extends OkaeriConfig {
    public CritDamageStat critDamageStat = CritDamageStat.builder()
                                       .id("critic_damage")
                                       .displayName("&9☠ Critic Damage")
                                       .description(Arrays.asList("&a%chance%% &7of extra damage you", "&7deal when landing critical hit..."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(11)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGRhZmIyM2VmYzU3ZjI1MTg3OGU1MzI4ZDExY2IwZWVmODdiNzljODdiMjU0YTdlYzcyMjk2ZjkzNjNlZjdjIn19fQ==")
                                               .mainMenuLore(Collections.singletonList("%skill_critic_chance_description%"))
                                               .build())
                                       .damagePercentagePerLevel(2)
                                       .baseAmount(0)
                                       .build();
}
