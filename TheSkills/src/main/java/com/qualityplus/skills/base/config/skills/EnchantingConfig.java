package com.qualityplus.skills.base.config.skills;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import com.qualityplus.assistant.util.faster.FastMap;
import com.qualityplus.assistant.util.number.NumberUtil;
import com.qualityplus.skills.base.reward.StatReward;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.skill.skills.EnchantingSkill;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Utility class for Enchanting config
 */
@Getter
@Setter
@Configuration(path = "skills/enchanting_skill.yml")
@Header("================================")
@Header("       Enchanting      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class EnchantingConfig extends OkaeriConfig {

    private String id = "enchanting";
    private boolean enabled = true;
    private String displayName = "Enchanting";
    private List<String> description = Collections.singletonList("&7Earn xp enchanting items!");
    private int maxLevel = 50;
    private Map<Integer, Double> xpRequirements = getLevelsMap();
    private Map<Integer, List<String>> skillInfoInGUI = getInfo();
    private Map<Integer, List<StatReward>> statRewards = getInternalRewards();
    private Map<Integer, List<String>> skillInfoInMessage = getInfo();
    private Map<Integer, List<CommandReward>> commandRewards = new HashMap<>();

    private GUIOptions guiOptions = GUIOptions.builder()
            .slot(23)
            .item(XMaterial.PLAYER_HEAD)
            .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2RjOTg1Y" +
                    "TdhNjhjNTc0ZjY4M2MwYjg1OTUyMWZlYjNmYzNkMmZmYTA1ZmEwOWRiMGJhZTQ0YjhhYzI5YjM4NSJ9fX0=")
            .mainMenuLore(Arrays.asList("&7Abilities To Upgrade:",
                    "&8» %skill_intelligence_displayname%",
                    "&8» %skill_defense_displayname%",
                    "",
                    "&7Your Stats:",
                    "&8» &r&6%skill_orb_master_displayname%",
                    "   &7%skill_orb_master_description%",
                    "&8» &r&6%skill_lightning_punch_displayname%",
                    "   &7%skill_lightning_punch_description%"
            ))
            .build();

    /**
     * Adds a skill
     *
     * @return {@link EnchantingSkill}
     */
    public Skill getSkill() {
        return EnchantingSkill.builder()
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
                .rewardsPerLevel(ImmutableMap.<Integer, Double>builder()
                        .put(1, 2D)
                        .put(3, 3D)
                        .put(4, 4D)
                        .put(5, 5D)
                        .build())
                .rewardForAllEnchantments(1)
                .build();
    }


    private Map<Integer, List<String>> getInfo() {
        return FastMap.listBuilder(Integer.class, String.class)
                .put(1, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+1 %skill_intelligence_displayname%",
                        "&8» &f+1 %skill_defense_displayname%",
                        "",
                        "&7Your Stats:",
                        "&8» &r&6%skill_orb_master_displayname%",
                        "   &7%skill_orb_master_description%",
                        "&8» &r&6%skill_lightning_punch_displayname%",
                        "   &7%skill_lightning_punch_description%"))
                .put(10, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+2 %skill_intelligence_displayname%",
                        "&8» &f+2 %skill_defense_displayname%",
                        "",
                        "&7Your Stats:",
                        "&8» &r&6%skill_orb_master_displayname%",
                        "   &7%skill_orb_master_description%",
                        "&8» &r&6%skill_lightning_punch_displayname%",
                        "   &7%skill_lightning_punch_description%"))
                .build();
    }

    private Map<Integer, List<StatReward>> getInternalRewards() {
        return FastMap.listBuilder(Integer.class, StatReward.class)
                .put(1, Arrays.asList(new StatReward("intelligence", 1), new StatReward("defense", 1),
                        new StatReward("enchantment_master", 1), new StatReward("orb_master", 1)))
                .put(10, Arrays.asList(new StatReward("intelligence", 2), new StatReward("defense", 2),
                        new StatReward("enchantment_master", 1), new StatReward("orb_master", 1)))
                .build();
    }

    private Map<Integer, Double> getLevelsMap() {
        final Map<Integer, Double> levels = new HashMap<>();

        NumberUtil.intStream(0, this.maxLevel).forEach(n -> levels.put(n, n * 15d));

        return levels;
    }

}
