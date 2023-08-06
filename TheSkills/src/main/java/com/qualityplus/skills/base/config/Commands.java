package com.qualityplus.skills.base.config;

import com.qualityplus.assistant.api.commands.details.CommandDetails;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.Collections;

/**
 * Utility class for  commands
 */
@Getter
@Setter
@Configuration(path = "commands.yml")
@Header("================================")
@Header("       Commands      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Commands extends OkaeriConfig {
    private CommandDetails reloadCommand = new CommandDetails(Collections.singletonList("reload"),
            "Reload files", "/TheSkills reload", "theskills.reload", true, Duration.ZERO.getSeconds(), true, "theskills");
    private CommandDetails mainMenuCommand = new CommandDetails(Collections.singletonList("mainmenu"),
            "Main Menu Command", "/TheSkills mainmenu", "theskills.mainmenu", true, Duration.ZERO.getSeconds(), true, "theskills");
    private CommandDetails skillMenuCommand = new CommandDetails(Collections.singletonList("skillmenu"),
            "Specific Skill Menu Command", "/TheSkills skillmenu", "theskills.skillmenu", true, Duration.ZERO.getSeconds(), true, "theskills");
    private CommandDetails addCommand = new CommandDetails(Collections.singletonList("add"),
            "Add Skill or Stat level", "/TheSkills add <player> <object> <amount>", "theskills.add", true, Duration.ZERO.getSeconds(), true, "theskills");
    private CommandDetails removeCommand = new CommandDetails(Collections.singletonList("remove"),
            "Remove Skill or Stat level", "/TheSkills remove " +
            "<player> <object> <amount>", "theskills.remove", true,
            Duration.ZERO.getSeconds(), true, "theskills");
    private CommandDetails statsCommand = new CommandDetails(Collections.singletonList("stats"),
            "Stats Menu Command", "/TheSkills stats", "theskills.stats", true, Duration.ZERO.getSeconds(), true, "theskills");
    private CommandDetails perksCommand = new CommandDetails(Collections.singletonList("perks"),
            "Perks Menu Command", "/TheSkills perks", "theskills.perks", true, Duration.ZERO.getSeconds(), true, "theskills");
    private CommandDetails helpCommand = new CommandDetails(Collections.singletonList("help"),
            "Show all commands", "/TheSkills help", "theskills.help", true, Duration.ZERO.getSeconds(), true, "theskills");
    private CommandDetails resetCommand = new CommandDetails(Collections.singletonList("reset"),
            "Reset Skills and Stats", "/TheSkills reset <player>", "theskills.reset", true, Duration.ZERO.getSeconds(), true, "theskills");

}
