package com.qualityplus.skills.base.config.perk;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.perks.SpidermanPerk;
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


@Configuration(path = "perks/spiderman.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Spiderman Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class SpidermanConfig extends OkaeriConfig {
    public SpidermanPerk spidermanPerk = SpidermanPerk.builder()
                                       .id("spiderman")
                                       .displayName("Spiderman")
                                       .description(Arrays.asList("  &a%percent%% &7chance to trap mobs in", "  &7a web for &a%duration% &7seconds while", "  &7in combat."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(31)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDU5NzZmZTMyZmM1ZDEwNTg5ZGZhODhkMDM2M2FkYmM5ODJlYjM1ZjhjZmNkMGUwYzg5M2E2MDk5NjY1YTg3NyJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_spiderman_description%"))
                                               .build())
                                       .chancePerLevel(0.1)
                                       .secondsDuration(2)
                                       .canBeUsedWithPlayers(false)
                                       .enabledWorlds(Arrays.asList("world", "world_nether", "world_the_end"))
                                       .build();
}
