package com.qualityplus.skills.base.config.skills;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import com.qualityplus.assistant.util.faster.FastMap;
import com.qualityplus.assistant.util.number.NumberUtil;
import com.qualityplus.skills.base.reward.StatReward;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.skill.skills.FarmingSkill;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Utility class for farming config
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Configuration(path = "skills/farming_skill.yml")
@Header("================================")
@Header("       Farming      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class FarmingConfig extends OkaeriConfig {
    private String id = "farming";
    private boolean enabled = true;
    private String displayName = "Farming";
    private List<String> description = Collections.singletonList("&7Harvest Crops to earn xp!");
    private int maxLevel = 50;
    private Map<Integer, Double> xpRequirements = getLevelsMap();
    private Map<Integer, List<String>> skillInfoInGUI = getInfo();
    private Map<Integer, List<StatReward>> statRewards = getInternalRewards();
    private Map<Integer, List<String>> skillInfoInMessage = getInfo();
    private Map<Integer, List<CommandReward>> commandRewards = new HashMap<>();
    private Map<XMaterial, Double> rewards = ImmutableMap.<XMaterial, Double>builder()
            .put(XMaterial.WHEAT, 2D)
            .put(XMaterial.CARROTS, 4D)
            .put(XMaterial.POTATOES, 5D)
            .build();
    private Map<XMaterial, Double> minionXpRewards = ImmutableMap.<XMaterial, Double>builder()
            .put(XMaterial.WHEAT, 2D)
            .put(XMaterial.CARROTS, 4D)
            .put(XMaterial.POTATOES, 5D)
            .build();

    private GUIOptions guiOptions = GUIOptions.builder()
            .slot(24)
            .item(XMaterial.PLAYER_HEAD)
            .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWFm" +
                    "MzI4Yzg3YjA2ODUwOWFjYTk4MzRlZmFjZTE5NzcwNWZlNWQ0ZjA4NzE3MzFiN2IyMWNkOTliOWZkZGMifX19=")
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
            .build();

    /**
     * Adds a skill
     *
     * @return {@link FarmingSkill}
     */
    public Skill getSkill() {
        return FarmingSkill.builder()
                .id(this.id)
                .enabled(this.enabled)
                .displayName(this.displayName)
                .description(this.description)
                .xpRequirements(this.xpRequirements)
                .skillInfoInGUI(this.skillInfoInGUI)
                .statRewards(this.statRewards)
                .skillInfoInMessage(this.skillInfoInMessage)
                .skillInfoInGUI(this.skillInfoInGUI)
                .commandRewards(this.commandRewards)
                .maxLevel(this.maxLevel)
                .skillGUIOptions(this.guiOptions)
                .rewards(this.rewards)
                .minionXpRewards(this.minionXpRewards)
                .build();
    }

    private Map<Integer, List<String>> getInfo() {
        return FastMap.listBuilder(Integer.class, String.class)
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

    private Map<Integer, List<StatReward>> getInternalRewards() {
        return FastMap.listBuilder(Integer.class, StatReward.class)
                .put(1, Arrays.asList(new StatReward("strength", 1), new StatReward("critic_damage", 1),
                        new StatReward("farming_fortune", 1), new StatReward("eagle_eyes", 1)))
                .put(10, Arrays.asList(new StatReward("strength", 2), new StatReward("critic_damage", 2),
                        new StatReward("farming_fortune", 1), new StatReward("eagle_eyes", 1)))
                .build();
    }

    private Map<Integer, Double> getLevelsMap() {
        final Map<Integer, Double> levels = new HashMap<>();

        NumberUtil.intStream(0, this.maxLevel).forEach(n -> levels.put(n, n * 15d));

        return levels;
    }


}
