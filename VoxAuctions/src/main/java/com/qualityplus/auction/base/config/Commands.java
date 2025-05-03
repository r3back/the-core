package com.qualityplus.auction.base.config;

import com.qualityplus.assistant.api.commands.details.CommandDetails;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;

import java.time.Duration;
import java.util.Collections;

/**
 * Utility class for commands
 */
@Configuration(path = "commands.yml")
@Header("================================")
@Header("       Commands      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Commands extends OkaeriConfig {
    private @Getter CommandDetails reloadCommand = new CommandDetails(Collections.singletonList("reload"),
            "Reload files",
            "/TheAuction reload",
            "theauction.reload",
            true,
            Duration.ZERO.getSeconds(),
            true,
            "theauction");
    private @Getter CommandDetails openCommand = new CommandDetails(Collections.singletonList("open"),
            "Open auction gui",
            "/TheAuction open",
            "theauction.open",
            true,
            Duration.ZERO.getSeconds(),
            true,
            "theauction");
    private @Getter CommandDetails helpCommand = new CommandDetails(Collections.singletonList("help"),
            "Show all commands",
            "/TheAuction help",
            "theauction.help",
            true,
            Duration.ZERO.getSeconds(),
            true,
            "theauction");
}
