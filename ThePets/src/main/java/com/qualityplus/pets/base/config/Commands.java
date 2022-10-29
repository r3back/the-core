package com.qualityplus.pets.base.config;

import com.qualityplus.assistant.api.commands.details.CommandDetails;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import eu.okaeri.platform.core.annotation.Configuration;
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
    public CommandDetails reloadCommand = new CommandDetails(Collections.singletonList("reload"), "Reload files", "/TheEnchanting reload", "theenchanting.reload", true, Duration.ZERO.getSeconds(), true, "thepets");
    public CommandDetails openCommand = new CommandDetails(Collections.singletonList("open"), "Open Enchanting GUI", "/TheEnchanting open", "theenchanting.open", true, Duration.ZERO.getSeconds(), true, "thepets");
    public CommandDetails helpCommand = new CommandDetails(Collections.singletonList("help"), "Show all commands", "/TheEnchanting help", "theenchanting.help", true, Duration.ZERO.getSeconds(), true, "thepets");
    public CommandDetails giveEggCommand = new CommandDetails(Collections.singletonList("giveegg"), "Give egg to a player", "/TheEnchanting giveegg <player> <id>", "theenchanting.giveegg", true, Duration.ZERO.getSeconds(), true, "thepets");
}
