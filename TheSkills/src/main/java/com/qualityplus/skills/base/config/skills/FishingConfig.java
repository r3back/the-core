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
import com.qualityplus.skills.base.skill.skills.FishingSkill;
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
@Configuration(path = "skills/fishing.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Fishing      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class FishingConfig extends OkaeriConfig {
    public FishingSkill fishingSkill = FishingSkill.builder()
            .id("fishing")
            .enabled(true)
            .displayName("Fishing")
            .description(Arrays.asList("&7Earn xp by fishing!"))
            .statRewards(new StatRewards(ImmutableMap.<Integer, List<Reward>>builder()
                    .put(1, Arrays.asList(new StatReward("speed", 1), new StatReward("magic_find", 1), new StatReward("fishing_fortune", 1), new StatReward("iron_lungs", 1)))
                    .put(10, Arrays.asList(new StatReward("speed", 2), new StatReward("magic_find", 2), new StatReward("fishing_fortune", 1), new StatReward("iron_lungs", 1)))
                    .build()))
            .commandRewards(new CommandRewards(new HashMap<>()))
            .skillGUIOptions(GUIOptions.builder()
                            .slot(25)
                            .item(XMaterial.PLAYER_HEAD)
                            .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzJhZDNjYzZkMzYzMWNhYTg4N2E5MWViYzVlNmE2NWNmMjU3ODAzYzdjN2FjZDU3ZDE5YTBhYzIyZmFlODQwMyJ9fX0=")
                            .mainMenuLore(Arrays.asList("&7Abilities To Upgrade:",
                                    "&8» %skill_speed_displayname%",
                                    "&8» %skill_magic_find_displayname%",
                                    "",
                                    "&7Your Stats:",
                                    "&8» &r&6%skill_sea_fortune_displayname%",
                                    "   &7%skill_sea_fortune_description%",
                                    "&8» &r&6%skill_iron_lungs_displayname%",
                                    "   &7%skill_iron_lungs_description%"))
                            .build())
            .skillsInfoInGUI(getInfo())
            .skillsInfoInMessage(getInfo())
            .xpRequirements(SkillsConfig.getRequirements())
            .maxLevel(50)
            .rewards(ImmutableMap.<String, Double>builder()
                    .put("COD", 2D)
                    .build())
            .rewardsForAllCaught(2D)
            .build();

    private Map<Integer, List<String>> getInfo(){
        return ImmutableMap.<Integer, List<String>>builder()
                .put(1, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+1 %skill_speed_displayname%",
                        "&8» &f+1 %skill_magic_find_displayname%",
                        "",
                        "&7Your Stats:",
                        "&8» &r&6%skill_sea_fortune_displayname%",
                        "   &7%skill_sea_fortune_description%",
                        "&8» &r&6%skill_iron_lungs_displayname%",
                        "   &7%skill_iron_lungs_description%"))
                .put(10, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+2 %skill_speed_displayname%",
                        "&8» &f+2 %skill_magic_find_displayname%",
                        "",
                        "&7Your Stats:",
                        "&8» &r&6%skill_sea_fortune_displayname%",
                        "   &7%skill_sea_fortune_description%",
                        "&8» &r&6%skill_iron_lungs_displayname%",
                        "   &7%skill_iron_lungs_description%"))
                .build();
    }
}
