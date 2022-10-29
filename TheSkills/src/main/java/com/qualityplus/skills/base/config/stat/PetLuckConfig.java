package com.qualityplus.skills.base.config.stat;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.serdes.registry.SerdesSkillsRegistry;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.stats.PetLuckStat;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;


@Configuration(path = "stats/pet_luck.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Pet Luck Stat      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class PetLuckConfig extends OkaeriConfig {
    public PetLuckStat petLuckStat = PetLuckStat.builder()
                                       .id("pet_luck")
                                       .displayName("&d♣ Pet Luck")
                                       .description(Arrays.asList("&a%chance% &7chance to get better pets", "&7when crafting."))
                                       .enabled(true)
                                       .skillGUIOptions(GUIOptions.builder()
                                               .slot(16)
                                               .page(1)
                                               .item(XMaterial.PLAYER_HEAD)
                                               .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTllY2JkY2I1NTQxMjNmYTRkMzE4NzY1MzhiYzdmYjI0NzQ5NGFlYTMyNWJkMjY1OTU2OTQ1MDVhZWJkMTBlZCJ9fX0=")
                                               .mainMenuLore(Collections.singletonList("%skill_intelligence_description%"))
                                               .build())
                                       .baseAmount(0)
                                       .build();
}
