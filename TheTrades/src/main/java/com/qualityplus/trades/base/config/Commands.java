package com.qualityplus.trades.base.config;

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
    public CommandDetails reloadCommand = new CommandDetails(Collections.singletonList("reload"), "Reload files", "/TheTrades reload", "thetrades.reload", true, Duration.ZERO.getSeconds(), true, "thetrades");
    public CommandDetails openCommand = new CommandDetails(Collections.singletonList("open"), "Open Trades gui", "/TheTrades open", "thetrades.open", true, Duration.ZERO.getSeconds(), true, "thetrades");
    public CommandDetails helpCommand = new CommandDetails(Collections.singletonList("help"), "Show all commands", "/TheTrades help", "thetrades.help", true, Duration.ZERO.getSeconds(), true, "thetrades");

}
