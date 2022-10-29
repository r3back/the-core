package com.qualityplus.skills.base.config.skills;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import com.qualityplus.skills.base.config.skills.common.SkillsConfig;
import com.qualityplus.skills.base.serdes.registry.SerdesSkillsRegistry;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.assistant.api.common.rewards.commands.CommandRewards;
import com.qualityplus.skills.base.reward.StatRewards;
import com.qualityplus.skills.base.skill.skills.RunecraftingSkill;
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
@Configuration(path = "skills/runecrafting.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       RuneCrafting      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class RunecraftingConfig extends OkaeriConfig {
    public RunecraftingSkill runecraftingSkill = RunecraftingSkill.builder()
            .id("runecrafting")
            .enabled(true)
            .displayName("RuneCrafting")
            .description(Arrays.asList("&7Earn xp by using rune table!"))
            .statRewards(new StatRewards(new HashMap<>()))
            .commandRewards(new CommandRewards(new HashMap<>()))
            .skillGUIOptions(GUIOptions.builder()
                    .slot(31)
                    .item(XMaterial.PLAYER_HEAD)
                    .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTAyNjc3MDUzZGM1NDI0NWRhYzRiMzk5ZDE0YWFlMjFlZTcxYTAxMGJkOWMzMzZjOGVjZWUxYTBkYmU4ZjU4YiJ9fX0=")
                    .mainMenuLore(Arrays.asList("&7Your Stats:",
                            "&8» &7Access to Level &5%skill_level_number% &7Runes"))
                    .build())
            .skillsInfoInGUI(getInfo())
            .skillsInfoInMessage(getInfo())
            .xpRequirements(SkillsConfig.getRequirements())
            .reward(2D)
            .maxLevel(50)
            .build();

    private Map<Integer, List<String>> getInfo(){
        return ImmutableMap.<Integer, List<String>>builder()
                .put(1, Arrays.asList(
                        "&7Your Stats:",
                        "&8» &7Access to Level &5%skill_level_number% &7Runes"))
                .put(10, Arrays.asList(
                        "&7Your Stats:",
                        "&8» &7Access to Level &5%skill_level_number% &7Runes"))
                .build();
    }
}
