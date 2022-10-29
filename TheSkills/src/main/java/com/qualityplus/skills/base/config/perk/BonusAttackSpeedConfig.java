package com.qualityplus.skills.base.config.perk;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.perks.BonusAttackSpeedPerk;
import com.qualityplus.skills.base.serdes.registry.SerdesSkillsRegistry;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;


@Configuration(path = "perks/bonus_attack_speed.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Bonus Attack Speed Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class BonusAttackSpeedConfig extends OkaeriConfig {
    public BonusAttackSpeedPerk bonusAttackSpeedPerk = BonusAttackSpeedPerk.builder()
                                       .id("bonus_attack_speed")
                                       .displayName("Bonus Attack Speed")
                                       .description(Arrays.asList("&aSoon..."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(19)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzQ5MmNhOTQwNzkxMzZkMjUyNTcwM2QzNzVjMjU1N2VhYzIwMWVlN2RkMzljZTExYzY0YTljMzgxNDdlY2M0ZCJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_bonus_attack_speed_description%"))
                                               .build())
                                       .chancePerLevel(0.1)
                                       .initialAmount(0)
                                       .build();
}
