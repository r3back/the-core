package com.qualityplus.auction.base.config;

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
    public CommandDetails reloadCommand = new CommandDetails(Collections.singletonList("reload"), "Reload files", "/TheAuction reload", "theauction.reload", true, Duration.ZERO.getSeconds(), true, "theauction");
    public CommandDetails openCommand = new CommandDetails(Collections.singletonList("open"), "Open auction gui", "/TheAuction open", "theauction.open", true, Duration.ZERO.getSeconds(), true, "theauction");
    public CommandDetails helpCommand = new CommandDetails(Collections.singletonList("help"), "Show all commands", "/TheAuction help", "theauction.help", true, Duration.ZERO.getSeconds(), true, "theauction");
}
