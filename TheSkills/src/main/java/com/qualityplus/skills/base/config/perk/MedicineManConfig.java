package com.qualityplus.skills.base.config.perk;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.perks.MedicineManPerk;
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


@Configuration(path = "perks/medicine_man.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Medicine Man Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class MedicineManConfig extends OkaeriConfig {
    public MedicineManPerk medicineManPerk = MedicineManPerk.builder()
                                       .id("medicine_man")
                                       .displayName("Medicine Man")
                                       .description(Arrays.asList("  &a%percent%% &7chance to regen &a%regen_percent%% &7of", "  &7your life while in combat."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(20)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGY5MTkwN2VjYzllMjNkNTRhYmY4ZDNhYmMyNDU1ZmU3YmZlNzU5ODBiNDdjYTkyOTQ5MDFlZGU0ZGI4NGE0ZiJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_medicine_man_description%"))
                                               .build())
                                       .chancePerLevel(0.1)
                                       .initialAmount(0)
                                       .healPercentageToRegeneratePerLevel(0.1)
                                       .healPercentageToRegenerateBase(0)
                                       .build();
}
