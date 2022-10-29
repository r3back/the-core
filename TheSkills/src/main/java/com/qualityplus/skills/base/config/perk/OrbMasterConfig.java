package com.qualityplus.skills.base.config.perk;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.perks.OrbMasterPerk;
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


@Configuration(path = "perks/orb_master.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Orb Master Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class OrbMasterConfig extends OkaeriConfig {
    public OrbMasterPerk orbMasterPerk = OrbMasterPerk.builder()
                                       .id("orb_master")
                                       .displayName("Orb Master")
                                       .description(Arrays.asList("  &a%percent%% &7chance to get double", "  &7experience orbs."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(24)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWMzYTRiNTFhNDgwMDI4OTBmYjI3MWNkYzE1MTdlYWY0MWFiMDAyNTNjMWI3ZDgyNDI3MjczNmJiNDE2NjNkNSJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_orb_master_description%"))
                                               .build())
                                       .chancePerLevel(0.1)
                                       .initialAmount(0)
                                       .build();
}
