package com.qualityplus.runes.base.config;

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
    public CommandDetails reloadCommand = new CommandDetails(Collections.singletonList("reload"), "Reload files", "/TheRunes reload", "therunes.reload", true, Duration.ZERO.getSeconds(), true, "therunes");
    public CommandDetails openCommand = new CommandDetails(Collections.singletonList("open"), "Open Trades gui", "/TheRunes open", "therunes.open", true, Duration.ZERO.getSeconds(), true, "therunes");
    public CommandDetails helpCommand = new CommandDetails(Collections.singletonList("help"), "Show all commands", "/TheRunes help", "therunes.help", true, Duration.ZERO.getSeconds(), true, "therunes");
    public CommandDetails giveRuneTableCommand = new CommandDetails(Collections.singletonList("giverunetable"), "Give Rune Table", "/TheRunes giverunetable <player>", "therunes.giverunetable", true, Duration.ZERO.getSeconds(), true, "therunes");
    public CommandDetails giveRemoverToolCommand = new CommandDetails(Collections.singletonList("giveremovertool"), "Give Remover Tool", "/TheRunes giveremovertool <player>", "therunes.giveremovertool", true, Duration.ZERO.getSeconds(), true, "therunes");
    public CommandDetails giveRuneCommand = new CommandDetails(Collections.singletonList("giverune"), "Give Rune", "/TheRunes giverune <player> <rune>", "therunes.giverune", true, Duration.ZERO.getSeconds(), true, "therunes");
    public CommandDetails runesCommand = new CommandDetails(Collections.singletonList("runes"), "Open all runes gui command", "/TheRunes runes", "therunes.runes", true, Duration.ZERO.getSeconds(), true, "therunes");
    public CommandDetails removalCommand = new CommandDetails(Collections.singletonList("removal"), "Open removal gui", "/TheRunes removal", "therunes.removal", true, Duration.ZERO.getSeconds(), true, "therunes");

}
