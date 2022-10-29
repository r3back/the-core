package com.qualityplus.skills.base.config.skills;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import com.qualityplus.skills.base.config.skills.common.SkillsConfig;
import com.qualityplus.skills.base.serdes.registry.SerdesSkillsRegistry;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.assistant.api.common.rewards.commands.CommandRewards;
import com.qualityplus.skills.base.reward.StatRewards;
import com.qualityplus.skills.base.skill.skills.DungeoneeringSkill;
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
@Configuration(path = "skills/dungeoneering.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Dungeoneering      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class DungeoneeringConfig extends OkaeriConfig {
    public DungeoneeringSkill dungeoneeringSkill = DungeoneeringSkill.builder()
            .id("dungeoneering")
            .enabled(true)
            .displayName("Dungeoneering")
            .description(Arrays.asList("&7Earn xp by killing bosses!"))
            .statRewards(new StatRewards(new HashMap<>()))
            .commandRewards(new CommandRewards(new HashMap<>()))
            .skillGUIOptions(GUIOptions.builder()
                    .slot(32)
                    .item(XMaterial.PLAYER_HEAD)
                    .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjA1MGRlNTcyY2Y4YmYxNDk2YjUyNzg1YWQzNDlkMDJhY2RkYTY0NDc5YjFiZTc1MDkzZTlhMWY3OTI4ZGQyIn19fQ=")
                    .mainMenuLore(Arrays.asList("&7Abilities To Upgrade:",
                            "&8» %skill_speed_displayname%",
                            "&8» %skill_ferocity_displayname%",
                            "",
                            "&7Your Stats:",
                            "&8» &r&6%skill_steel_skin_displayname%",
                            "   &7%skill_steel_skin_description%",
                            "&8» &r&6%skill_wizard_displayname%",
                            "   &7%skill_wizard_description%"
                    ))
                    .build())
            .skillsInfoInGUI(getInfo())
            .skillsInfoInMessage(getInfo())
            .xpRequirements(SkillsConfig.getRequirements())
            .rewardsByName(new HashMap<>())
            .mythicMobRewards(new HashMap<String, Double>(){{
                put("mythicMobIdExample", 10D);
                put("mythicMobIdExample2", 20D);

            }})
            .maxLevel(50)
            .build();

    private Map<Integer, List<String>> getInfo(){
        return ImmutableMap.<Integer, List<String>>builder()
                .put(1, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+1 %skill_speed_displayname%",
                        "&8» &f+1 %skill_ferocity_displayname%",
                        "",
                        "&7Your Stats:",
                        "&8» &r&6%skill_steel_skin_displayname%",
                        "   &7%skill_steel_skin_description%",
                        "&8» &r&6%skill_wizard_displayname%",
                        "   &7%skill_wizard_description%"))
                .put(10, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+2 %skill_speed_displayname%",
                        "&8» &f+2 %skill_ferocity_displayname%",
                        "",
                        "&7Your Stats:",
                        "&8» &r&6%skill_steel_skin_displayname%",
                        "   &7%skill_steel_skin_description%",
                        "&8» &r&6%skill_wizard_displayname%",
                        "   &7%skill_wizard_description%"))
                .build();
    }
}
