package com.qualityplus.anvil.base.config;

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
    public CommandDetails reloadCommand = new CommandDetails(Collections.singletonList("reload"), "Reload files", "/TheAnvil reload", "theanvil.reload", true, Duration.ZERO.getSeconds(), true, "theanvil");
    public CommandDetails helpCommand = new CommandDetails(Collections.singletonList("help"), "Show all commands", "/TheAnvil help", "theanvil.help", true, Duration.ZERO.getSeconds(), true, "theanvil");
    public CommandDetails openCommand = new CommandDetails(Collections.singletonList("open"), "Open anvil gui", "/TheAnvil open", "theanvil.open", true, Duration.ZERO.getSeconds(), true, "theanvil");

}
