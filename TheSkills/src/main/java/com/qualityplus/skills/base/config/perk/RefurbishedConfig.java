package com.qualityplus.skills.base.config.perk;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.perks.RefurbishedPerk;
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


@Configuration(path = "perks/refurbished.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Refurbished Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class RefurbishedConfig extends OkaeriConfig {
    public RefurbishedPerk refurbishedPerk = RefurbishedPerk.builder()
                                       .id("refurbished")
                                       .displayName("Refurbished")
                                       .description(Arrays.asList("  &a%percent%% &7chance to refurbish your", "  &7pickaxes restoring their max", "  &7durability while breaking blocks."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(29)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWNiZDlmNWVjMWVkMDA3MjU5OTk2NDkxZTY5ZmY2NDlhMzEwNmNmOTIwMjI3YjFiYjNhNzFlZTdhODk4NjNmIn19fQ==")
                                               .mainMenuLore(Collections.singletonList("%skill_refurbished_description%"))
                                               .build())
                                       .chancePerLevel(0.1)
                                       .toolList(Arrays.asList(
                                                XMaterial.DIAMOND_PICKAXE,
                                                XMaterial.IRON_PICKAXE,
                                                XMaterial.GOLDEN_PICKAXE
                                               ))
                                       .build();
}
