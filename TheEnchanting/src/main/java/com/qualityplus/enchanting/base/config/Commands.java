package com.qualityplus.enchanting.base.config;

import com.qualityplus.assistant.api.commands.details.CommandDetails;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Exclude;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;
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
    public CommandDetails reloadCommand = new CommandDetails(Collections.singletonList("reload"), "Reload files", "/TheEnchanting reload", "theenchanting.reload", true, Duration.ZERO.getSeconds(), true, "theenchanting");
    public CommandDetails openCommand = new CommandDetails(Collections.singletonList("open"), "Open Enchanting GUI", "/TheEnchanting open", "theenchanting.open", true, Duration.ZERO.getSeconds(), true, "theenchanting");
    public CommandDetails helpCommand = new CommandDetails(Collections.singletonList("help"), "Show all commands", "/TheEnchanting help", "theenchanting.help", true, Duration.ZERO.getSeconds(), true, "theenchanting");
    @Exclude
    public CommandDetails testCommand = new CommandDetails(Collections.singletonList("test"), "test", "/TheEnchanting test", "theenchanting.test", true, Duration.ZERO.getSeconds(), true, "theenchanting");
}
