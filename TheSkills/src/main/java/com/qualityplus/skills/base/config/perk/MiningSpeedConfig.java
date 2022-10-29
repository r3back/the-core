package com.qualityplus.skills.base.config.perk;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.serdes.registry.SerdesSkillsRegistry;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.perk.perks.MiningSpeedPerk;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;


@Configuration(path = "perks/mining_speed.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Mining Speed Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class MiningSpeedConfig extends OkaeriConfig {
    public MiningSpeedPerk miningSpeedPerk = MiningSpeedPerk.builder()
                                       .id("mining_speed")
                                       .displayName("Mining Speed")
                                       .description(Arrays.asList("  &a%percent%% &7chance to get Haste for", "  &7&a%duration% &7seconds when mining ores."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(22)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWE4YzY4NTE0ZDhjODE5ZTY4ZWI3NWI1OTY0NDVkODhiMzY3YWUzNTFiYWE5OTgzYmM2OWNmNmI1MjBmMzk4NSJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_mining_speed_description%"))
                                               .build())
                                       .chancePerLevel(0.1)
                                       .baseSecondsDuration(0)
                                       .secondsDurationPerLevel(1)
                                       .initialAmount(0)
                                       .build();
}
