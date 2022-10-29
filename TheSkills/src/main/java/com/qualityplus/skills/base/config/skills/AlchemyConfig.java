package com.qualityplus.skills.base.config.skills;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import com.qualityplus.assistant.api.common.rewards.Reward;
import com.qualityplus.skills.base.reward.StatReward;
import com.qualityplus.skills.base.config.skills.common.SkillsConfig;
import com.qualityplus.skills.base.serdes.registry.SerdesSkillsRegistry;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.assistant.api.common.rewards.commands.CommandRewards;
import com.qualityplus.skills.base.reward.StatRewards;
import com.qualityplus.skills.base.skill.skills.AlchemySkill;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration(path = "skills/alchemy.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Alchemy      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class AlchemyConfig extends OkaeriConfig {
    public AlchemySkill alchemySkill = AlchemySkill.builder()
            .id("alchemy")
            .enabled(true)
            .displayName("Alchemy")
            .description(Arrays.asList("&7Brew Potions to earn alchemy xp!"))
            .statRewards(new StatRewards(ImmutableMap.<Integer, List<Reward>>builder()
                    .put(1, Arrays.asList(new StatReward("defense", 1), new StatReward("ferocity", 1), new StatReward("brew_chance", 1), new StatReward("potion_master", 1)))
                    .put(10, Arrays.asList(new StatReward("defense", 2), new StatReward("ferocity", 2), new StatReward("brew_chance", 1), new StatReward("potion_master", 1)))
                    .build()))
            .commandRewards(new CommandRewards(new HashMap<>()))
            .skillGUIOptions(GUIOptions.builder()
                    .slot(19)
                    .item(XMaterial.PLAYER_HEAD)
                    .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzQ5MmNhOTQwNzkxMzZkMjUyNTcwM2QzNzVjMjU1N2VhYzIwMWVlN2RkMzljZTExYzY0YTljMzgxNDdlY2M0ZCJ9fX0=")
                    .mainMenuLore(Arrays.asList("&7Abilities To Upgrade:",
                            "&8» %skill_defense_displayname%",
                            "&8» %skill_ferocity_displayname%",
                            "",
                            "&7Your Stats:",
                            "&8» &r&6%skill_brew_chance_displayname%",
                            "   &7%skill_brew_chance_description%",
                            "&8» &r&6%skill_potion_master_displayname%",
                            "   &7%skill_potion_master_description%"
                            ))
                    .build())
            .skillsInfoInGUI(getInfo())
            .skillsInfoInMessage(getInfo())
            .xpRequirements(SkillsConfig.getRequirements())
            .maxLevel(50)
            .rewards(getRewards())
            .build();

    private Map<Integer, List<String>> getInfo(){
        return ImmutableMap.<Integer, List<String>>builder()
                .put(1, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+1 %skill_defense_displayname%",
                        "&8» &f+1 %skill_ferocity_displayname%",
                        "",
                        "&7Your Stats:",
                        "&8» &r&6%skill_brew_chance_displayname%",
                        "   &7%skill_brew_chance_description%",
                        "&8» &r&6%skill_potion_master_displayname%",
                        "   &7%skill_potion_master_description%"))
                .put(10, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+2 %skill_defense_displayname%",
                        "&8» &f+2 %skill_ferocity_displayname%",
                        "",
                        "&7Your Stats:",
                        "&8» &r&6%skill_brew_chance_displayname%",
                        "   &7%skill_brew_chance_description%",
                        "&8» &r&6%skill_potion_master_displayname%",
                        "   &7%skill_potion_master_description%"))
                .build();
    }

    private Map<XMaterial, Double> getRewards(){
        return ImmutableMap.<XMaterial, Double>builder()
                .put(XMaterial.GHAST_TEAR, 5D)
                .put(XMaterial.GLOWSTONE_DUST, 5D)
                .put(XMaterial.FERMENTED_SPIDER_EYE, 5D)
                .put(XMaterial.GUNPOWDER, 5D)
                .put(XMaterial.REDSTONE, 5D)
                .put(XMaterial.NETHER_WART, 5D)
                .put(XMaterial.PUFFERFISH, 5D)
                .put(XMaterial.DRAGON_BREATH, 5D)
                .put(XMaterial.SUGAR, 5D)
                .put(XMaterial.RABBIT_FOOT, 5D)
                .put(XMaterial.MAGMA_BLOCK, 5D)
                .put(XMaterial.SPIDER_EYE, 5D)
                .put(XMaterial.PHANTOM_MEMBRANE, 5D)
                .build();
    }


}
