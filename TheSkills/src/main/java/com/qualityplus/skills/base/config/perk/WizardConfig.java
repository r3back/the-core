package com.qualityplus.skills.base.config.perk;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.perks.WizardPerk;
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


@Configuration(path = "perks/wizard.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Wizard Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class WizardConfig extends OkaeriConfig {
    public WizardPerk wizardPerk = WizardPerk.builder()
                                       .id("wizard")
                                       .displayName("Wizard")
                                       .description(Arrays.asList("  &a%percent%% &7chance to infect enemies with", "  &7Nausea %potion_level_roman% for &a%duration% &7seconds while in", "  &7combat."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(33)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDk0OGJhNzM5YWQyNWU3MDU5NGU2OTQ4YTQyOGE1Y2Q4MTljZDFhODBlYzBiZTllYWNiYmZhNzkxNWM0YzM4OCJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_eagle_eyes_description%"))
                                               .build())
                                       .chancePerLevel(0.1)
                                       .baseSecondsDuration(0)
                                       .secondsDurationPerLevel(1)
                                       .level(1)
                                       .initialAmount(0)
                                       .build();
}
