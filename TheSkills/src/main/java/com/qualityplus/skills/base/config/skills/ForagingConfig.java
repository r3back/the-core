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
import com.qualityplus.skills.base.skill.skills.ForagingSkill;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Configuration(path = "skills/foraging.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Foraging      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class ForagingConfig extends OkaeriConfig {
    public ForagingSkill foragingSkill = ForagingSkill.builder()
            .id("foraging")
            .enabled(true)
            .displayName("Foraging")
            .description(Arrays.asList("&7Cut wood to earn foraging xp!"))
            .statRewards(new StatRewards(ImmutableMap.<Integer, List<Reward>>builder()
                    .put(1, Arrays.asList(new StatReward("strength", 1), new StatReward("defense", 1), new StatReward("foraging_fortune", 1), new StatReward("medicine_man", 1)))
                    .put(10, Arrays.asList(new StatReward("strength", 2), new StatReward("defense", 2), new StatReward("foraging_fortune", 1), new StatReward("medicine_man", 1)))
                    .build()))
            .commandRewards(new CommandRewards(new HashMap<>()))
            .skillGUIOptions(GUIOptions.builder()
                    .slot(29)
                    .item(XMaterial.PLAYER_HEAD)
                    .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWY0OTc3ODZkN2JhMTkyMzY5OTMyOTY2Njg2YWE5ZmI3MTQ3ZmE1ODg0ZjlhNjIwOWM0YTczZTJkNjBmMzlmNSJ9fX0=")
                    .mainMenuLore(Arrays.asList("&7Abilities To Upgrade:",
                            "&8» %skill_strength_displayname%",
                            "&8» %skill_defense_displayname%",
                            "",
                            "&7Your Stats:",
                            "&8» &r&6%skill_foraging_fortune_displayname%",
                            "   &7%skill_foraging_fortune_description%",
                            "&8» &r&6%skill_medicine_man_displayname%",
                            "   &7%skill_medicine_man_description%"))
                    .build())
            .skillsInfoInGUI(getInfo())
            .skillsInfoInMessage(getInfo())
            .xpRequirements(SkillsConfig.getRequirements())
            .maxLevel(50)
            .rewards(ImmutableMap.<XMaterial, Double>builder()
                    .put(XMaterial.OAK_LOG, 2D)
                    .put(XMaterial.ACACIA_LOG, 2D)
                    .put(XMaterial.BIRCH_LOG, 2D)
                    .put(XMaterial.DARK_OAK_LOG, 2D)
                    .put(XMaterial.JUNGLE_LOG, 2D)
                    .put(XMaterial.SPRUCE_LOG, 2D)
                    .build())
            .build();

    private Map<Integer, List<String>> getInfo(){
        return ImmutableMap.<Integer, List<String>>builder()
                .put(1, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+1 %skill_strength_displayname%",
                        "&8» &f+1 %skill_defense_displayname%",
                        "",
                        "&7Your Stats:",
                        "&8» &r&6%skill_foraging_fortune_displayname%",
                        "   &7%skill_foraging_fortune_description%",
                        "&8» &r&6%skill_medicine_man_displayname%",
                        "   &7%skill_medicine_man_description%"))
                .put(10, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+2 %skill_strength_displayname%",
                        "&8» &f+2 %skill_defense_displayname%",
                        "",
                        "&7Your Stats:",
                        "&8» &r&6%skill_foraging_fortune_displayname%",
                        "   &7%skill_foraging_fortune_description%",
                        "&8» &r&6%skill_medicine_man_displayname%",
                        "   &7%skill_medicine_man_description%"))
                .build();
    }
}
