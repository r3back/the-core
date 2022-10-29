package com.qualityplus.souls.base.config;

import com.qualityplus.assistant.api.commands.details.CommandDetails;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.time.Duration;
import java.util.Collections;

@Configuration(path = "commands.yml")
@Header("================================")
@Header("       Commands      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Commands extends OkaeriConfig {
    public CommandDetails reloadCommand = new CommandDetails(Collections.singletonList("reload"), "Reload files", "/TheSouls reload", "thesouls.reload", true, Duration.ZERO.getSeconds(), true, "thesouls");
    public CommandDetails editCommand = new CommandDetails(Collections.singletonList("edit"), "Open souls edit gui", "/TheSouls edit", "thesouls.edit", true, Duration.ZERO.getSeconds(), true, "thesouls");
    public CommandDetails giveSoulCommand = new CommandDetails(Collections.singletonList("givesoul"), "Give Souls to players", "/TheSouls givesoul <player>", "thesouls.givesoul", true, Duration.ZERO.getSeconds(), true, "thesouls");
    public CommandDetails tiaCommand = new CommandDetails(Collections.singletonList("tia"), "Open souls tia gui", "/TheSouls tia", "thesouls.tia", true, Duration.ZERO.getSeconds(), true, "thesouls");
    public CommandDetails helpCommand = new CommandDetails(Collections.singletonList("help"), "Show all commands", "/TheSouls help", "thesouls.help", true, Duration.ZERO.getSeconds(), true, "thesouls");

}
