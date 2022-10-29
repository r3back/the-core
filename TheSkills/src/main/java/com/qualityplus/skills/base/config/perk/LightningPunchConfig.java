package com.qualityplus.skills.base.config.perk;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.perks.LightningPunchPerk;
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


@Configuration(path = "perks/lightning_punch.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Lightning Punch Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class LightningPunchConfig extends OkaeriConfig {
    public LightningPunchPerk lightningPunchPerk = LightningPunchPerk.builder()
                                       .id("lightning_punch")
                                       .displayName("Lightning Punch")
                                       .description(Arrays.asList("  &a%percent%% &7chance to strike a lightning", "  &7that makes &a%damage% &7damage while", "  &7fighting."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(19)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzg1YzE5YmVhMWUwYWExYjNmOGY5ODUzYzc1NGI1MzQ2NzcxOGNlMGY5MGRlODg2ZmQ0ZWYyYjQyMDk5NTVjNSJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_lightning_punch_description%"))
                                               .build())
                                       .chancePerLevel(0.1)
                                       .initialAmount(0)
                                       .damage(5)
                                       .build();
}
