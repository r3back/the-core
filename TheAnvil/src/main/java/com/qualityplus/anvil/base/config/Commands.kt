package com.qualityplus.anvil.base.config

import com.qualityplus.assistant.api.commands.details.CommandDetails
import eu.okaeri.configs.OkaeriConfig
import eu.okaeri.configs.annotation.Header
import eu.okaeri.configs.annotation.NameModifier
import eu.okaeri.configs.annotation.NameStrategy
import eu.okaeri.configs.annotation.Names
import eu.okaeri.platform.core.annotation.Configuration
import lombok.*
import java.time.Duration

@Getter
@Setter
@Configuration(path = "commands.yml")
@Header("================================")
@Header("       Commands      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
class Commands : OkaeriConfig() {
    var reloadCommand = CommandDetails(
        listOf("reload"),
        "Reload files",
        "/TheAnvil reload",
        "theanvil.reload",
        true,
        Duration.ZERO.seconds,
        true,
        "theanvil"
    )
    var helpCommand = CommandDetails(
        listOf("help"),
        "Show all commands",
        "/TheAnvil help",
        "theanvil.help",
        true,
        Duration.ZERO.seconds,
        true,
        "theanvil"
    )
    var openCommand = CommandDetails(
        listOf("open"),
        "Open anvil gui",
        "/TheAnvil open",
        "theanvil.open",
        true,
        Duration.ZERO.seconds,
        true,
        "theanvil"
    )
}