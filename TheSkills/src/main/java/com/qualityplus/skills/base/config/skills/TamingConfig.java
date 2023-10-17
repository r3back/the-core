package com.qualityplus.skills.base.config.skills;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import com.qualityplus.assistant.util.faster.FastMap;
import com.qualityplus.assistant.util.number.NumberUtil;
import com.qualityplus.skills.base.reward.StatReward;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.skill.skills.TamingSkill;
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
 * Utility class for taming config
 */
@Getter
@Setter
@Configuration(path = "skills/taming_skill.yml")
@Header("================================")
@Header("       Taming      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class TamingConfig extends OkaeriConfig {

    private String id = "taming";
    private boolean enabled = true;
    private String displayName = "Taming";
    private List<String> description = Collections.singletonList("&7Earn xp by levelling your pet!");
    private int maxLevel = 50;
    private Map<Integer, Double> xpRequirements = getLevelsMap();
    private Map<Integer, List<String>> skillInfoInGUI = getInfo();
    private Map<Integer, List<StatReward>> statRewards = getInternalRewards();
    private Map<Integer, List<String>> skillInfoInMessage = getInfo();
    private Map<Integer, List<CommandReward>> commandRewards = new HashMap<>();
    private GUIOptions guiOptions = GUIOptions.builder()
            .slot(33)
            .item(XMaterial.PLAYER_HEAD)
            .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM" +
                    "jU5MDAxYTg1MWJiMWI5ZTljMDVkZTVkNWM2OGIxZWEwZGM4YmQ4NmJhYmYxODhlMGFkZWQ4ZjkxMmMwN2QwZCJ9fX0=")
            .mainMenuLore(Arrays.asList("&7Abilities To Upgrade:",
                    "&8» %skill_ferocity_displayname%",
                    "&8» %skill_pet_luck_displayname%"))
            .build();

    /**
     * Adds a stat
     *
     * @return {@link TamingSkill}
     */
    public Skill getSkill() {
        return TamingSkill.builder()
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
                .build();
    }

    private Map<Integer, List<String>> getInfo() {
        return FastMap.listBuilder(Integer.class, String.class)
                .put(1, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+1 %skill_ferocity_displayname%",
                        "&8» &f+1 %skill_pet_luck_displayname%"))
                .put(10, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+2 %skill_ferocity_displayname%",
                        "&8» &f+2 %skill_pet_luck_displayname%"))
                .build();
    }

    private Map<Integer, List<StatReward>> getInternalRewards() {
        return FastMap.listBuilder(Integer.class, StatReward.class)
                .put(1, Arrays.asList(new StatReward("ferocity", 1), new StatReward("pet_luck", 1)))
                .put(10, Arrays.asList(new StatReward("ferocity", 2), new StatReward("pet_luck", 2)))
                .build();
    }

    private Map<Integer, Double> getLevelsMap() {
        final Map<Integer, Double> levels = new HashMap<>();

        NumberUtil.intStream(0, this.maxLevel).forEach(n -> levels.put(n, n * 15d));

        return levels;
    }

}
