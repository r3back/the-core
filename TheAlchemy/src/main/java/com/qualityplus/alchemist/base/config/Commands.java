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

/**
 * Commands file representation
 */
@Getter
@Setter
@Configuration(path = "commands.yml")
@Header("================================")
@Header("       Commands      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Commands extends OkaeriConfig {
    private CommandDetails reloadCommand = CommandDetails.builder()
            .aliases(Collections.singletonList("reload"))
            .description("Reload files")
            .syntax("/TheAlchemist reload")
            .permission("thealchemist.reload")
            .onlyForPlayers(true)
            .cooldownInSeconds(Duration.ZERO.getSeconds())
            .enabled(true)
            .labelProvider("thealchemist")
            .build();

    private CommandDetails createCommand = CommandDetails.builder()
            .aliases(Collections.singletonList("create"))
            .description("Create recipe command")
            .syntax("/TheAlchemist create <id>")
            .permission("thealchemist.create")
            .onlyForPlayers(true)
            .cooldownInSeconds(Duration.ZERO.getSeconds())
            .enabled(true)
            .labelProvider("thealchemist")
            .build();

    private CommandDetails recipesCommand = CommandDetails.builder()
            .aliases(Collections.singletonList("recipes"))
            .description("Open all recipes gui")
            .syntax("/TheAlchemist recipes")
            .permission("thealchemist.recipes")
            .onlyForPlayers(true)
            .cooldownInSeconds(Duration.ZERO.getSeconds())
            .enabled(true)
            .labelProvider("thealchemist")
            .build();

    private CommandDetails openCommand = CommandDetails.builder()
            .aliases(Collections.singletonList("open"))
            .description("Open Stand gui")
            .syntax("/TheAlchemist open")
            .permission("thealchemist.open")
            .onlyForPlayers(true)
            .cooldownInSeconds(Duration.ZERO.getSeconds())
            .enabled(true)
            .labelProvider("thealchemist")
            .build();

    private CommandDetails deleteCommand = CommandDetails.builder()
            .aliases(Collections.singletonList("delete"))
            .description("Delete recipe command")
            .syntax("/TheAlchemist delete <id>")
            .permission("thealchemist.delete")
            .onlyForPlayers(true)
            .cooldownInSeconds(Duration.ZERO.getSeconds())
            .enabled(true)
            .labelProvider("thealchemist")
            .build();

    private CommandDetails helpCommand = CommandDetails.builder()
            .aliases(Collections.singletonList("help"))
            .description("Show all commands")
            .syntax("/TheAlchemist help")
            .permission("thealchemist.help")
            .onlyForPlayers(true)
            .cooldownInSeconds(Duration.ZERO.getSeconds())
            .enabled(true)
            .labelProvider("thealchemist")
            .build();
}
