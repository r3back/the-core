package com.qualityplus.skills.base.config.perk;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.perks.CactusSkinPerk;
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


@Configuration(path = "perks/cactus_skin.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Cactus Skin Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class CactusSkinConfig extends OkaeriConfig {
    public CactusSkinPerk cactusSkinPerk = CactusSkinPerk.builder()
                                       .id("cactus_skin")
                                       .displayName("Cactus Skin")
                                       .description(Arrays.asList("  &a%percent%% &7chance deal &a+%damage% &7to enemies", "  &7when they punch you."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(11)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjEyZGUzYzE5NTQyNDczYjdiMzQ0MWRmZTdkZDM2MGQyNDJlZDMwZGE3NmI0ODUzZDhhMjQxN2I4NTUyM2ZmYyJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_eagle_eyes_description%"))
                                               .build())
                                       .chancePerLevel(0.1)
                                       .damageBase(0)
                                       .damagePerLevel(0.2)
                                       .initialAmount(0)
                                       .build();
}
