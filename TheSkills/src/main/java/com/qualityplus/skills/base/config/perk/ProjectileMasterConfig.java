package com.qualityplus.skills.base.config.perk;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.perks.ProjectileMasterPerk;
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

@Configuration(path = "perks/projectile_master.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Projectile Master Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class ProjectileMasterConfig extends OkaeriConfig {
    public ProjectileMasterPerk projectileMasterPerk = ProjectileMasterPerk.builder()
                                       .id("projectile_master")
                                       .displayName("Projectile Master")
                                       .description(Arrays.asList("&a%percent%% &7chance to make your projectiles &a%projectile_percent%% &7more stronger."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(28)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzQ5MmNhOTQwNzkxMzZkMjUyNTcwM2QzNzVjMjU1N2VhYzIwMWVlN2RkMzljZTExYzY0YTljMzgxNDdlY2M0ZCJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_one_punch_man_description%"))
                                               .build())
                                       .chancePerLevel(0.1)
                                       .initialAmount(0)
                                       .extraDamagePercentageBase(0)
                                       .extraDamagePercentageBasePerLevel(1)
                                       .build();
}
