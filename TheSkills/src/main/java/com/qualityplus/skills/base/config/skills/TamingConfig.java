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
import com.qualityplus.skills.base.skill.skills.TamingSkill;
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

@Configuration(path = "skills/taming.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Taming      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class TamingConfig extends OkaeriConfig {
    public TamingSkill tamingSkill = TamingSkill.builder()
            .id("taming")
            .enabled(true)
            .displayName("Taming")
            .description(Arrays.asList("&7Earn xp by levelling your pet!"))
            .statRewards(new StatRewards(ImmutableMap.<Integer, List<Reward>>builder()
                    .put(1, Arrays.asList(new StatReward("ferocity", 1), new StatReward("pet_luck", 1)))
                    .put(10, Arrays.asList(new StatReward("ferocity", 2), new StatReward("pet_luck", 2)))
                    .build()))
            .commandRewards(new CommandRewards(new HashMap<>()))
            .skillGUIOptions(GUIOptions.builder()
                    .slot(33)
                    .item(XMaterial.PLAYER_HEAD)
                    .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjU5MDAxYTg1MWJiMWI5ZTljMDVkZTVkNWM2OGIxZWEwZGM4YmQ4NmJhYmYxODhlMGFkZWQ4ZjkxMmMwN2QwZCJ9fX0=")
                    .mainMenuLore(Arrays.asList("&7Abilities To Upgrade:",
                            "&8» %skill_ferocity_displayname%",
                            "&8» %skill_pet_luck_displayname%"))
                    .build())
            .skillsInfoInGUI(getInfo())
            .skillsInfoInMessage(getInfo())
            .xpRequirements(SkillsConfig.getRequirements())
            .maxLevel(50)
            .build();

    private Map<Integer, List<String>> getInfo(){
        return ImmutableMap.<Integer, List<String>>builder()
                .put(1, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+1 %skill_ferocity_displayname%",
                        "&8» &f+1 %skill_pet_luck_displayname%"))
                .put(10, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+2 %skill_ferocity_displayname%",
                        "&8» &f+2 %skill_pet_luck_displayname%"))
                .build();
    }
}
