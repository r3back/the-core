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
import com.qualityplus.skills.base.skill.skills.MiningSkill;
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
@Configuration(path = "skills/mining.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Mining      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class MiningConfig extends OkaeriConfig {
    public MiningSkill miningSkill = MiningSkill.builder()
            .id("mining")
            .enabled(true)
            .displayName("Mining")
            .description(Arrays.asList("&7Earn xp by mining ores!"))
            .statRewards(new StatRewards(ImmutableMap.<Integer, List<Reward>>builder()
                    .put(1, Arrays.asList(new StatReward("strength", 1), new StatReward("magic_find", 1), new StatReward("mining_fortune", 1), new StatReward("mining_speed", 1)))
                    .put(10, Arrays.asList(new StatReward("strength", 2), new StatReward("magic_find", 2), new StatReward("mining_fortune", 1), new StatReward("mining_speed", 1)))
                    .build()))
            .commandRewards(new CommandRewards(new HashMap<>()))
            .skillGUIOptions(GUIOptions.builder()
                    .slot(30)
                    .item(XMaterial.PLAYER_HEAD)
                    .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTgzNjhkZmFmOTQ4NGUxMjNkMzBhNTc2ODI2ZTk3NjNkZDg2Mzg0MThjZGNhYmFkZjYxMzZjZTQ1NzQ5YWExMSJ9fX0=")
                    .mainMenuLore(Arrays.asList("&7Abilities To Upgrade:",
                            "&8» %skill_strength_displayname%",
                            "&8» %skill_magic_find_displayname%",
                            "",
                            "&7Your Stats:",
                            "&8» &r&6%skill_mining_fortune_displayname%",
                            "   &7%skill_mining_fortune_description%",
                            "&8» &r&6%skill_mining_speed_displayname%",
                            "   &7%skill_mining_speed_description%"
                    ))
                    .build())
            .skillsInfoInGUI(getInfo())
            .skillsInfoInMessage(getInfo())
            .xpRequirements(SkillsConfig.getRequirements())
            .maxLevel(50)
            .rewards(ImmutableMap.<XMaterial, Double>builder()
                    .put(XMaterial.COAL_ORE, 2D)
                    .put(XMaterial.IRON_ORE, 2D)
                    .put(XMaterial.STONE, 2D)
                    .put(XMaterial.COBBLESTONE, 2D)
                    .put(XMaterial.DIAMOND_ORE, 2D)
                    .put(XMaterial.REDSTONE_ORE, 2D)
                    .build())
            .build();

    private Map<Integer, List<String>> getInfo(){
        return ImmutableMap.<Integer, List<String>>builder()
                .put(1, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+1 %skill_strength_displayname%",
                        "&8» &f+1 %skill_magic_find_displayname%",
                        "",
                        "&7Your Stats:",
                        "&8» &r&6%skill_mining_fortune_displayname%",
                        "   &7%skill_mining_fortune_description%",
                        "&8» &r&6%skill_mining_speed_displayname%",
                        "   &7%skill_mining_speed_description%"))
                .put(10, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+2 %skill_strength_displayname%",
                        "&8» &f+2 %skill_magic_find_displayname%",
                        "",
                        "&7Your Stats:",
                        "&8» &r&6%skill_mining_fortune_displayname%",
                        "   &7%skill_mining_fortune_description%",
                        "&8» &r&6%skill_mining_speed_displayname%",
                        "   &7%skill_mining_speed_description%"))
                .build();
    }
}
