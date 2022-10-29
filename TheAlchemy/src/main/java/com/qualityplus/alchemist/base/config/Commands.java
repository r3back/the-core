package com.qualityplus.alchemist.base.config;

import com.qualityplus.assistant.api.commands.details.CommandDetails;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
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
    public CommandDetails reloadCommand = new CommandDetails(Collections.singletonList("reload"), "Reload files", "/TheAlchemist reload", "thealchemist.reload", true, Duration.ZERO.getSeconds(), true, "thealchemist");
    public CommandDetails createCommand = new CommandDetails(Collections.singletonList("create"), "Create recipe command", "/TheAlchemist create <id>", "thealchemist.create", true, Duration.ZERO.getSeconds(), true, "thealchemist");
    public CommandDetails recipesCommand = new CommandDetails(Collections.singletonList("recipes"), "Open all recipes gui", "/TheAlchemist recipes", "thealchemist.recipes", true, Duration.ZERO.getSeconds(), true, "thealchemist");
    public CommandDetails openCommand = new CommandDetails(Collections.singletonList("open"), "Open Stand gui", "/TheAlchemist open", "thealchemist.open", true, Duration.ZERO.getSeconds(), true, "thealchemist");
    public CommandDetails deleteCommand = new CommandDetails(Collections.singletonList("delete"), "Delete recipe command", "/TheAlchemist delete <id>", "thealchemist.delete", true, Duration.ZERO.getSeconds(), true, "thealchemist");
    public CommandDetails helpCommand = new CommandDetails(Collections.singletonList("help"), "Show all commands", "/TheAlchemist help", "thealchemist.help", true, Duration.ZERO.getSeconds(), true, "thealchemist");

}
