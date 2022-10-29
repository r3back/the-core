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
import com.qualityplus.skills.base.skill.skills.DiscovererSkill;
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
@Configuration(path = "skills/discoverer.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Discoverer      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class DiscovererConfig extends OkaeriConfig {
    public DiscovererSkill discovererSkill = DiscovererSkill.builder()
            .id("discoverer")
            .enabled(true)
            .displayName("Discoverer")
            .description(Arrays.asList("&7Earn xp discovering the world!"))
            .statRewards(new StatRewards(ImmutableMap.<Integer, List<Reward>>builder()
                    .put(1, Arrays.asList(new StatReward("speed", 1), new StatReward("ferocity", 1), new StatReward("leaves_master", 1), new StatReward("cactus_skin", 1)))
                    .put(10, Arrays.asList(new StatReward("speed", 2), new StatReward("ferocity", 2), new StatReward("leaves_master", 1), new StatReward("cactus_skin", 1)))
                    .build()))
            .commandRewards(new CommandRewards(new HashMap<>()))
            .skillGUIOptions(GUIOptions.builder()
                    .slot(22)
                    .item(XMaterial.PLAYER_HEAD)
                    .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY5MTk2YjMzMGM2Yjg5NjJmMjNhZDU2MjdmYjZlY2NlNDcyZWFmNWM5ZDQ0Zjc5MWY2NzA5YzdkMGY0ZGVjZSJ9fX0=")
                    .mainMenuLore(Arrays.asList("&7Abilities To Upgrade:",
                            "&8» %skill_speed_displayname%",
                            "&8» %skill_ferocity_displayname%",
                            "",
                            "&7Your Stats:",
                            "&8» &r&6%skill_leaves_master_displayname%",
                            "   &7%skill_leaves_master_description%",
                            "&8» &r&6%skill_cactus_skin_displayname%",
                            "   &7%skill_cactus_skin_description%"
                    ))
                    .build())
            .skillsInfoInGUI(getInfo())
            .skillsInfoInMessage(getInfo())
            .xpRequirements(SkillsConfig.getRequirements())
            .maxLevel(50)
            .rewardsPerBlocksWalked(ImmutableMap.<Integer, Double>builder()
                    .put(40, 2D)
                    .build())
            .build();

    private Map<Integer, List<String>> getInfo(){
        return ImmutableMap.<Integer, List<String>>builder()
                .put(1, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+1 %skill_speed_displayname%",
                        "&8» &f+1 %skill_ferocity_displayname%",
                        "",
                        "&7Your Stats:",
                        "&8» &r&6%skill_leaves_master_displayname%",
                        "   &7%skill_leaves_master_description%",
                        "&8» &r&6%skill_cactus_skin_displayname%",
                        "   &7%skill_cactus_skin_description%"))
                .put(10, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+2 %skill_speed_displayname%",
                        "&8» &f+2 %skill_ferocity_displayname%",
                        "",
                        "&7Your Stats:",
                        "&8» &r&6%skill_leaves_master_displayname%",
                        "   &7%skill_leaves_master_description%",
                        "&8» &r&6%skill_cactus_skin_displayname%",
                        "   &7%skill_cactus_skin_description%"))
                .build();
    }
}
