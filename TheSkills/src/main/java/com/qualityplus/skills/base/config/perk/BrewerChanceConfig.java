package com.qualityplus.skills.base.config.perk;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XPotion;
import com.google.common.collect.ImmutableMap;
import com.qualityplus.skills.base.serdes.registry.SerdesSkillsRegistry;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.perk.perks.BrewChancePerk;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;


@Configuration(path = "perks/brew_chance.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Brew Chance Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class BrewerChanceConfig extends OkaeriConfig {
    public BrewChancePerk brewerChancePerk = BrewChancePerk.builder()
            .id("brew_chance")
            .displayName("Brew Chance")
            .description(Arrays.asList("  &a%percent%% &7chance to get extra potion", "  &7effect when consume a potion", "  &7for &a%duration% &7seconds."))
            .enabled(true)
            .skillGUIOptions(GUIOptions.builder()
                    .slot(10)
                    .page(1)
                    .item(XMaterial.PLAYER_HEAD)
                    .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzQ5MmNhOTQwNzkxMzZkMjUyNTcwM2QzNzVjMjU1N2VhYzIwMWVlN2RkMzljZTExYzY0YTljMzgxNDdlY2M0ZCJ9fX0=")
                    .mainMenuLore(Collections.singletonList("%skill_brew_chance_description%"))
                    .build())
            .chancePerLevel(1)
            .secondsDurationPerLevel(1)
            .baseSecondsDuration(1)
            .potionAndChances(ImmutableMap.<XPotion, Double>builder()
                    .put(XPotion.ABSORPTION, 2D)
                    .put(XPotion.CONFUSION, 4D)
                    .put(XPotion.CONDUIT_POWER, 5D)
                    .build())
            .baseAmount(0)
            .build();
}
