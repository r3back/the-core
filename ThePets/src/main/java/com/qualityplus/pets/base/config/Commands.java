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
    public CommandDetails reloadCommand = new CommandDetails(Collections.singletonList("reload"), "Reload files", "/ThePets reload", "thepets.reload", true, Duration.ZERO.getSeconds(), true, "thepets");
    public CommandDetails openCommand = new CommandDetails(Collections.singletonList("open"), "Open Pets GUI", "/ThePets open", "thepets.open", true, Duration.ZERO.getSeconds(), true, "thepets");
    public CommandDetails helpCommand = new CommandDetails(Collections.singletonList("help"), "Show all commands", "/ThePets help", "thepets.help", true, Duration.ZERO.getSeconds(), true, "thepets");
    public CommandDetails giveEggCommand = new CommandDetails(Collections.singletonList("giveegg"), "Give pet egg to a player", "/ThePets giveegg <player> <id>", "thepets.giveegg", true, Duration.ZERO.getSeconds(), true, "thepets");

}
