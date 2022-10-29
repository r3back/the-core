package com.qualityplus.skills.base.config.skills;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import com.qualityplus.assistant.api.common.rewards.Reward;
import com.qualityplus.skills.base.reward.StatReward;
import com.qualityplus.skills.base.config.skills.common.SkillsConfig;
import com.qualityplus.skills.base.serdes.registry.SerdesSkillsRegistry;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.skill.skills.FarmingSkill;
import com.qualityplus.assistant.api.common.rewards.commands.CommandRewards;
import com.qualityplus.skills.base.reward.StatRewards;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Configuration(path = "skills/farming.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Farming      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class FarmingConfig extends OkaeriConfig {
    public FarmingSkill farmingSkill = FarmingSkill.builder()
            .id("farming")
            .enabled(true)
            .displayName("Farming")
            .description(Arrays.asList("&7Harvest Crops to earn xp!"))
            .statRewards(new StatRewards(ImmutableMap.<Integer, List<Reward>>builder()
                    .put(1, Arrays.asList(new StatReward("strength", 1), new StatReward("critic_damage", 1), new StatReward("farming_fortune", 1), new StatReward("eagle_eyes", 1)))
                    .put(10, Arrays.asList(new StatReward("strength", 2), new StatReward("critic_damage", 2), new StatReward("farming_fortune", 1), new StatReward("eagle_eyes", 1)))
                    .build()))
            .commandRewards(new CommandRewards(new HashMap<>()))
            .skillGUIOptions(GUIOptions.builder()
                    .slot(24)
                    .item(XMaterial.PLAYER_HEAD)
                    .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWFmMzI4Yzg3YjA2ODUwOWFjYTk4MzRlZmFjZTE5NzcwNWZlNWQ0ZjA4NzE3MzFiN2IyMWNkOTliOWZkZGMifX19=")
                    .mainMenuLore(Arrays.asList("&7Abilities To Upgrade:",
                            "&8» %skill_strength_displayname%",
                            "&8» %skill_critic_damage_displayname%",
                            "",
                            "&7Your Stats:",
                            "&8» &r&6%skill_farming_fortune_displayname%",
                            "   &7%skill_farming_fortune_description%",
                            "&8» &r&6%skill_eagle_eyes_displayname%",
                            "   &7%skill_eagle_eyes_description%"
                    ))
                    .build())
            .skillsInfoInGUI(getInfo())
            .skillsInfoInMessage(getInfo())
            .xpRequirements(SkillsConfig.getRequirements())
            .maxLevel(50)
            .rewards(ImmutableMap.<XMaterial, Double>builder()
                    .put(XMaterial.WHEAT, 2D)
                    .put(XMaterial.CARROTS, 4D)
                    .put(XMaterial.POTATOES, 5D)
                    .build())
            .build();


    private Map<Integer, List<String>> getInfo(){
        return ImmutableMap.<Integer, List<String>>builder()
                .put(1, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+1 %skill_strength_displayname%",
                        "&8» &f+1 %skill_critic_damage_displayname%",
                        "",
                        "&7Your Stats:",
                        "&8» &r&6%skill_farming_fortune_displayname%",
                        "   &7%skill_farming_fortune_description%",
                        "&8» &r&6%skill_eagle_eyes_displayname%",
                        "   &7%skill_eagle_eyes_description%"))
                .put(10, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+2 %skill_strength_displayname%",
                        "&8» &f+2 %skill_critic_damage_displayname%",
                        "",
                        "&7Your Stats:",
                        "&8» &r&6%skill_farming_fortune_displayname%",
                        "   &7%skill_farming_fortune_description%",
                        "&8» &r&6%skill_eagle_eyes_displayname%",
                        "   &7%skill_eagle_eyes_description%"))
                .build();
    }
}
