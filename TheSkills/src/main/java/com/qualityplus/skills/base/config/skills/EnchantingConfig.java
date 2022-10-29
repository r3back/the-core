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
import com.qualityplus.skills.base.skill.skills.EnchantingSkill;
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
@Configuration(path = "skills/enchanting.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Enchanting      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class EnchantingConfig extends OkaeriConfig {
    public EnchantingSkill enchantingSkill = EnchantingSkill.builder()
            .id("enchanting")
            .enabled(true)
            .displayName("Enchanting")
            .description(Arrays.asList("&7Earn xp enchanting items!"))
            .statRewards(new StatRewards(ImmutableMap.<Integer, List<Reward>>builder()
                    .put(1, Arrays.asList(new StatReward("intelligence", 1), new StatReward("defense", 1), new StatReward("enchantment_master", 1), new StatReward("orb_master", 1)))
                    .put(10, Arrays.asList(new StatReward("intelligence", 2), new StatReward("defense", 2), new StatReward("enchantment_master", 1), new StatReward("orb_master", 1)))
                    .build()))
            .commandRewards(new CommandRewards(new HashMap<>()))
            .skillGUIOptions(GUIOptions.builder()
                    .slot(23)
                    .item(XMaterial.PLAYER_HEAD)
                    .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2RjOTg1YTdhNjhjNTc0ZjY4M2MwYjg1OTUyMWZlYjNmYzNkMmZmYTA1ZmEwOWRiMGJhZTQ0YjhhYzI5YjM4NSJ9fX0=")
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
                    .build())
            .skillsInfoInGUI(getInfo())
            .skillsInfoInMessage(getInfo())
            .xpRequirements(SkillsConfig.getRequirements())
            .maxLevel(50)
            .rewardsPerLevel(ImmutableMap.<Integer, Double>builder()
                    .put(1, 2D)
                    .put(3, 3D)
                    .put(4, 4D)
                    .put(5, 5D)
                    .build())
            .rewardForAllEnchantments(1)
            .build();

    private Map<Integer, List<String>> getInfo(){
        return ImmutableMap.<Integer, List<String>>builder()
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
}
