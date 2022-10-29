package com.qualityplus.skills.base.config.perk;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.serdes.registry.SerdesSkillsRegistry;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.perk.perks.SeaFortunePerk;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;


@Configuration(path = "stats/sea_fortune.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Sea Fortune Stat      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class SeaFortuneConfig extends OkaeriConfig {
    public SeaFortunePerk seaFortunePerk = SeaFortunePerk.builder()
            .id("sea_fortune")
            .displayName("Sea Lucky Chance")
            .description(Arrays.asList("  &a%percent%% &7chance to get &a%multiplier%x &7drops", "  &7when fishing."))
            .enabled(true)
            .skillGUIOptions(GUIOptions.builder()
                    .slot(30)
                    .page(1)
                    .item(XMaterial.PLAYER_HEAD)
                    .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2IxNTU3YjcwMGRmNTI0ZTA3ODYwYzc0NjNlYWNhOTQ1MTViYWI3ZTRiMWQzM2UzOWJkMjg5NmFkY2IwZWQ5MCJ9fX0=")
                    .mainMenuLore(Collections.singletonList("%skill_sea_fortune_description%"))
                    .build())
            .initialAmount(0)
            .build();
}
