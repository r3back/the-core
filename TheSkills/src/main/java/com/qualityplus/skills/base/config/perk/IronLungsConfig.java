package com.qualityplus.skills.base.config.perk;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.perks.IronLungsPerk;
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

@Configuration(path = "perks/iron_lungs.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Iron Lungs Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class IronLungsConfig extends OkaeriConfig {
    public IronLungsPerk ironLungsPerk = IronLungsPerk.builder()
                                       .id("iron_lungs")
                                       .displayName("Iron Lungs")
                                       .description(Arrays.asList("  &a%percent%% &7chance to get Water Breathing %potion_level_roman%", "  &7for &a%duration% &7seconds while be in the water."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(15)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTczMTFiMWZjNzdmM2I3ZWQxYmNlNjVlM2ZiMjRkNzI2YTgzY2EyMTIzZGI0MzNiZWMxYzNmMzBhZTdmMThjNiJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_iron_lungs_description%"))
                                               .build())
                                       .chancePerLevel(0.1)
                                       .baseSecondsDuration(0)
                                       .secondsDurationPerLevel(1)
                                       .level(1)
                                       .initialAmount(0)
                                       .build();
}
