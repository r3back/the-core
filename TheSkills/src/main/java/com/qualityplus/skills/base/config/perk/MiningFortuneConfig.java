package com.qualityplus.skills.base.config.perk;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.perks.MiningFortunePerk;
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


@Configuration(path = "perks/mining_fortune.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Mining Fortune Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class MiningFortuneConfig extends OkaeriConfig {
    public MiningFortunePerk miningFortunePerk = MiningFortunePerk.builder()
                                       .id("mining_fortune")
                                       .displayName("Mining Fortune")
                                       .description(Arrays.asList("  &a%percent%% &7chance to get &a%multiplier%x &7drops", "  &7when break ores."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(21)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTAwZDc1YThjYzAzZTU5YzQ3NWNhYzc2ODFiYjRlNmQwOGFjNTkyOTBkNWYyMzlkNjA1Njc2MmI0ZTU1ZjkxNSJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_mining_fortune_description%"))
                                               .build())
                                       .chancePerLevel(0.1)
                                       .initialAmount(0)
                                       .allowedMaterials(Arrays.asList(
                                                XMaterial.COAL_ORE,
                                                XMaterial.DIAMOND_ORE,
                                                XMaterial.GOLD_ORE,
                                                XMaterial.IRON_ORE,
                                                XMaterial.REDSTONE_ORE,
                                                XMaterial.LAPIS_ORE
                                               ))
                                       .build();
}
