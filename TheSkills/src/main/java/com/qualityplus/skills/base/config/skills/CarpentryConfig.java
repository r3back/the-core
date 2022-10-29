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
import com.qualityplus.skills.base.skill.skills.CarpentrySkill;
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
@Configuration(path = "skills/carpentry.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Carpentry      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class CarpentryConfig extends OkaeriConfig {
    public CarpentrySkill carpentrySkill = CarpentrySkill.builder()
            .id("carpentry")
            .enabled(true)
            .displayName("Carpentry")
            .description(Arrays.asList("&7Earn XP Crafting objects!"))
            .statRewards(new StatRewards(ImmutableMap.<Integer, List<Reward>>builder()
                    .put(1, Arrays.asList(new StatReward("strength", 1), new StatReward("crit_chance", 1), new StatReward("refurbished", 1), new StatReward("medicine_man", 1)))
                    .put(10, Arrays.asList(new StatReward("strength", 2), new StatReward("crit_chance", 2), new StatReward("refurbished", 1), new StatReward("medicine_man", 1)))
                    .build()))
            .commandRewards(new CommandRewards(new HashMap<>()))
            .skillGUIOptions(GUIOptions.builder()
                    .slot(20)
                    .item(XMaterial.PLAYER_HEAD)
                    .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGMzNjA0NTIwOGY5YjVkZGNmOGM0NDMzZTQyNGIxY2ExN2I5NGY2Yjk2MjAyZmIxZTUyNzBlZThkNTM4ODFiMSJ9fX0=")
                    .mainMenuLore(Arrays.asList("&7Abilities To Upgrade:",
                            "&8» %skill_strength_displayname%",
                            "&8» %skill_critic_chance_displayname%",
                            "",
                            "&7Your Stats:",
                            "&8» &r&6%skill_refurbished_displayname%",
                            "   &7%skill_refurbished_description%",
                            "&8» &r&6%skill_medicine_man_displayname%",
                            "   &7%skill_medicine_man_description%"
                    ))
                    .build())
            .skillsInfoInGUI(getInfo())
            .skillsInfoInMessage(getInfo())
            .xpRequirements(SkillsConfig.getRequirements())
            .maxLevel(50)
            .rewards(ImmutableMap.<XMaterial, Double>builder()
                    .put(XMaterial.FURNACE, 2D)
                    .build())
            .xpForAllItems(0D)
            .build();

    private Map<Integer, List<String>> getInfo(){
        return ImmutableMap.<Integer, List<String>>builder()
                .put(1, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+1 %skill_strength_displayname%",
                        "&8» &f+1 %skill_critic_chance_displayname%",
                        "",
                        "&7Your Stats:",
                        "&8» &r&6%skill_refurbished_displayname%",
                        "   &7%skill_refurbished_description%",
                        "&8» &r&6%skill_medicine_man_displayname%",
                        "   &7%skill_medicine_man_description%"))
                .put(10, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+2 %skill_strength_displayname%",
                        "&8» &f+2 %skill_critic_chance_displayname%",
                        "",
                        "&7Your Stats:",
                        "&8» &r&6%skill_refurbished_displayname%",
                        "   &7%skill_refurbished_description%",
                        "&8» &r&6%skill_medicine_man_displayname%",
                        "   &7%skill_medicine_man_description%"))
                .build();
    }
}
