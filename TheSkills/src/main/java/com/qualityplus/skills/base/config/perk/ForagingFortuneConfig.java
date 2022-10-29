package com.qualityplus.skills.base.config.perk;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.perks.ForagingFortunePerk;
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


@Configuration(path = "perks/foraging_fortune.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Foraging Fortune Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class ForagingFortuneConfig extends OkaeriConfig {
    public ForagingFortunePerk foragingFortunePerk = ForagingFortunePerk.builder()
                                       .id("foraging_fortune")
                                       .displayName("Foraging Fortune")
                                       .description(Arrays.asList("  &a%percent%% &7chance to get &a%multiplier%x &7drops", "  &7when break logs."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(14)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmIzNTE2OWI3NjU5ZGM3MGYzZDc5MzQzYjM5YTA5Yjk1ZjQ0MzBhZTAxMWRlY2E2Y2FmMzU4YzIwZTkyZGM0YSJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_foraging_fortune_description%"))
                                               .build())
                                       .chancePerLevel(0.1)
                                       .initialAmount(0)
                                       .allowedMaterials(Arrays.asList(
                                                XMaterial.OAK_LOG,
                                                XMaterial.ACACIA_LOG,
                                                XMaterial.BIRCH_LOG,
                                                XMaterial.DARK_OAK_LOG,
                                                XMaterial.SPRUCE_LOG,
                                                XMaterial.JUNGLE_LOG
                                               ))
                                       .build();
}
