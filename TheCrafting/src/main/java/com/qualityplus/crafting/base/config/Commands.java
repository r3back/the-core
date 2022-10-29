package com.qualityplus.crafting.base.config;

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
    public CommandDetails reloadCommand = new CommandDetails(Collections.singletonList("reload"), "Reload files", "/TheCrafting reload", "thecrafting.reload", true, Duration.ZERO.getSeconds(), true, "thecrafting");
    public CommandDetails createCommand = new CommandDetails(Collections.singletonList("create"), "Create recipe command", "/TheCrafting create <id>", "thecrafting.create", true, Duration.ZERO.getSeconds(), true, "thecrafting");
    public CommandDetails recipesCommand = new CommandDetails(Collections.singletonList("recipes"), "Open all recipes gui", "/TheCrafting recipes", "thecrafting.recipes", true, Duration.ZERO.getSeconds(), true, "thecrafting");
    public CommandDetails bookCommand = new CommandDetails(Collections.singletonList("book"), "Open Recipes Book gui", "/TheCrafting book", "thecrafting.book", true, Duration.ZERO.getSeconds(), true, "thecrafting");
    public CommandDetails deleteCommand = new CommandDetails(Collections.singletonList("delete"), "Delete recipe command", "/TheCrafting delete <id>", "thecrafting.delete", true, Duration.ZERO.getSeconds(), true, "thecrafting");
    public CommandDetails previewCommand = new CommandDetails(Collections.singletonList("preview"), "View recipe preview", "/TheCrafting preview <id>", "thecrafting.preview", true, Duration.ZERO.getSeconds(), true, "thecrafting");
    public CommandDetails helpCommand = new CommandDetails(Collections.singletonList("help"), "Show all commands", "/TheCrafting help", "thecrafting.help", true, Duration.ZERO.getSeconds(), true, "thecrafting");

}
