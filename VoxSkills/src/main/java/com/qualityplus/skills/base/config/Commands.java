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

@Getter
@Setter
@Configuration(path = "commands.yml")
@Header("================================")
@Header("       Commands      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Commands extends OkaeriConfig {
    public CommandDetails reloadCommand = new CommandDetails(Collections.singletonList("reload"), "Reload files", "/VoxSkills reload", "theskills.reload", true, Duration.ZERO.getSeconds(), true, "theskills");
    public CommandDetails mainMenuCommand = new CommandDetails(Collections.singletonList("mainmenu"), "Main Menu Command", "/VoxSkills mainmenu", "theskills.mainmenu", true, Duration.ZERO.getSeconds(), true, "theskills");

    public CommandDetails skillMenuCommand = new CommandDetails(Collections.singletonList("skillmenu"), "Specific Skill Menu Command", "/VoxSkills skillmenu", "theskills.skillmenu", true, Duration.ZERO.getSeconds(), true, "theskills");

    public CommandDetails addCommand = new CommandDetails(Collections.singletonList("add"), "Add Skill or Stat level", "/VoxSkills add <player> <object> <amount>", "theskills.add", true, Duration.ZERO.getSeconds(), true, "theskills");
    public CommandDetails removeCommand = new CommandDetails(Collections.singletonList("remove"), "Remove Skill or Stat level", "/VoxSkills remove <player> <object> <amount>", "theskills.remove", true, Duration.ZERO.getSeconds(), true, "theskills");
    public CommandDetails statsCommand = new CommandDetails(Collections.singletonList("stats"), "Stats Menu Command", "/VoxSkills stats", "theskills.stats", true, Duration.ZERO.getSeconds(), true, "theskills");
    public CommandDetails perksCommand = new CommandDetails(Collections.singletonList("perks"), "Perks Menu Command", "/VoxSkills perks", "theskills.perks", true, Duration.ZERO.getSeconds(), true, "theskills");
    public CommandDetails helpCommand = new CommandDetails(Collections.singletonList("help"), "Show all commands", "/VoxSkills help", "theskills.help", true, Duration.ZERO.getSeconds(), true, "theskills");
    public CommandDetails resetCommand = new CommandDetails(Collections.singletonList("reset"), "Reset Skills and Stats", "/VoxSkills reset <player>", "theskills.reset", true, Duration.ZERO.getSeconds(), true, "theskills");
    public CommandDetails addPerkCommand = new CommandDetails(Collections.singletonList("addperk"), "Add Perk level", "/VoxSkills addperk <player> <object> <amount>", "theskills.addperk", true, Duration.ZERO.getSeconds(), true, "theskills");
    public CommandDetails removePerkCommand = new CommandDetails(Collections.singletonList("removeperk"), "Remove Perk level", "/VoxSkills removeperk <player> <object> <amount>", "theskills.removeperk", true, Duration.ZERO.getSeconds(), true, "theskills");
    public CommandDetails viewItemStatsCommand = new CommandDetails(Collections.singletonList("viewitemstats"), "View item stats", "/VoxSkills viewitemstats", "theskills.viewitemstats", true, Duration.ZERO.getSeconds(), true, "theskills");
    public CommandDetails addToItemCommand = new CommandDetails(Collections.singletonList("addtoitem"), "Add Stat or perk to item", "/VoxSkills addtoitem <object> <amount>", "theskills.addtoitem", true, Duration.ZERO.getSeconds(), true, "theskills");
    public CommandDetails removeFromItemCommand = new CommandDetails(Collections.singletonList("removefromitem"), "Remove Stat or Perk from item", "/VoxSkills removefromitem <object> <amount>", "theskills.removefromitem", true, Duration.ZERO.getSeconds(), true, "theskills");
}
