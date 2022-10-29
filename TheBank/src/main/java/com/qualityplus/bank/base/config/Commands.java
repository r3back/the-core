package com.qualityplus.bank.base.config;

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
    public CommandDetails reloadCommand = new CommandDetails(Collections.singletonList("reload"), "Reload files", "/TheBank reload", "thebank.reload", true, Duration.ZERO.getSeconds(), true, "thebank");
    public CommandDetails bankCommand = new CommandDetails(Collections.singletonList("open"), "Open bank gui", "/TheBank open", "thebank.open", true, Duration.ZERO.getSeconds(), true, "thebank");
    public CommandDetails givePersonalBankCommand = new CommandDetails(Collections.singletonList("givepersonal"), "Give personal bank to players", "/TheBank givepersonal <player>", "thebank.givepersonal", true, Duration.ZERO.getSeconds(), true, "thebank");
    public CommandDetails helpCommand = new CommandDetails(Collections.singletonList("help"), "Show all commands", "/TheBank help", "thebank.help", true, Duration.ZERO.getSeconds(), true, "thebank");

}
