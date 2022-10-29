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
import com.qualityplus.skills.base.skill.skills.CombatSkill;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.EntityType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Configuration(path = "skills/combat.yml", serdes = SerdesSkillsRegistry.class)
@Header("================================")
@Header("       Combat      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class CombatConfig extends OkaeriConfig {
    public CombatSkill combatSkill = CombatSkill.builder()
            .id("combat")
            .enabled(true)
            .displayName("Combat")
            .description(Arrays.asList("&7Earn xp by killing mobs!"))
            .statRewards(new StatRewards(ImmutableMap.<Integer, List<Reward>>builder()
                    .put(1, Arrays.asList(new StatReward("critic_damage", 1), new StatReward("critic_chance", 1), new StatReward("one_punch_man", 1), new StatReward("spiderman", 1)))
                    .put(10, Arrays.asList(new StatReward("critic_damage", 2), new StatReward("critic_chance", 2), new StatReward("one_punch_man", 1), new StatReward("spiderman", 1)))
                    .build()))
            .commandRewards(new CommandRewards(new HashMap<>()))
            .skillGUIOptions(GUIOptions.builder()
                    .slot(21)
                    .item(XMaterial.PLAYER_HEAD)
                    .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjQ1MjhiMzIyOTY2MGYzZGZhYjQyNDE0ZjU5ZWU4ZmQwMWU4MDA4MWRkM2RmMzA4Njk1MzZiYTliNDE0ZTA4OSJ9fX0=")
                    .mainMenuLore(Arrays.asList("&7Abilities To Upgrade:",
                            "&8» %skill_critic_damage_displayname%",
                            "&8» %skill_critic_chance_displayname%",
                            "",
                            "&7Your Stats:",
                            "&8» &r&6%skill_one_punch_man_displayname%",
                            "   &7%skill_one_punch_man_description%",
                            "&8» &r&6%skill_spiderman_displayname%",
                            "   &7%skill_spiderman_description%"))
                    .build())
            .skillsInfoInGUI(getInfo())
            .skillsInfoInMessage(getInfo())
            .xpRequirements(SkillsConfig.getRequirements())
            .maxLevel(50)
            .rewards(ImmutableMap.<EntityType, Double>builder()
                    .put(EntityType.ZOMBIE, 2D)
                    .put(EntityType.CREEPER, 3D)
                    .put(EntityType.ENDERMAN, 4D)

                    .build())
            .build();

    private Map<Integer, List<String>> getInfo(){
        return ImmutableMap.<Integer, List<String>>builder()
                .put(1, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+1 %skill_critic_damage_displayname%",
                        "&8» &f+1 %skill_critic_chance_displayname%",
                        "",
                        "&7Your Stats:",
                        "&8» &r&6%skill_one_punch_man_displayname%",
                        "   &7%skill_one_punch_man_description%",
                        "&8» &r&6%skill_spiderman_displayname%",
                        "   &7%skill_spiderman_description%"))
                .put(10, Arrays.asList("&7Abilities To Upgrade:",
                        "&8» &f+2 %skill_critic_damage_displayname%",
                        "&8» &f+2 %skill_critic_chance_displayname%",
                        "",
                        "&7Your Stats:",
                        "&8» &r&6%skill_one_punch_man_displayname%",
                        "   &7%skill_one_punch_man_description%",
                        "&8» &r&6%skill_spiderman_displayname%",
                        "   &7%skill_spiderman_description%"))
                .build();
    }
}
