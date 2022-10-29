package com.qualityplus.skills.base.config.perk;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.perks.PotionMasterPerk;
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

@Configuration(path = "perks/potion_master.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Potion Master Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class PotionMasterConfig extends OkaeriConfig {
    public PotionMasterPerk potionMasterPerk = PotionMasterPerk.builder()
                                       .id("potion_master")
                                       .displayName("Potion Master")
                                       .description(Arrays.asList("  &a%percent%% &7chance to get &a+%duration% &7seconds", "  &7when consume potions."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(25)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDEyYWMyMzlmMzliYTkxN2U5YmQ2YzE5ZDZlN2RjNDgzMTc5NjUxMDQ3ODdjOGJmY2YwOTBjMGMwMzI3N2FjOSJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_iron_lungs_description%"))
                                               .build())
                                       .chancePerLevel(0.1)
                                       .baseSecondsDuration(0)
                                       .secondsDurationPerLevel(1)
                                       .level(1)
                                       .initialAmount(0)
                                       .build();
}
