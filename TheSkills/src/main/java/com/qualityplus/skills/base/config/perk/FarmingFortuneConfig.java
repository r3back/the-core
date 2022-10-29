package com.qualityplus.skills.base.config.perk;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.perks.FarmingFortunePerk;
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


@Configuration(path = "perks/farming_fortune.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Farming Fortune Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class FarmingFortuneConfig extends OkaeriConfig {
    public FarmingFortunePerk farmingFortuneConfig = FarmingFortunePerk.builder()
                                       .id("farming_fortune")
                                       .displayName("Farming Fortune")
                                       .description(Arrays.asList("  &a%percent%% &7chance to get &a%multiplier%x &7drops", "  &7when break crops."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(13)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2FhNTk2NmExNDcyNDQ1MDRjYzU2ZWY2ZWZkMmQyZjQ0NzM4YjhmMDNkOTNhNjE3NjZhZjNmYzQ0ODdmOTgwYiJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_farming_fortune_description%"))
                                               .build())
                                       .chancePerLevel(0.1)
                                       .initialAmount(0)
                                       .allowedMaterials(Arrays.asList(
                                               XMaterial.WHEAT,
                                                XMaterial.CARROTS,
                                                XMaterial.POTATOES))
                                       .build();
}
