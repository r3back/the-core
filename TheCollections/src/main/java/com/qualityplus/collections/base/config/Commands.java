package com.qualityplus.collections.base.config;

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
    public CommandDetails reloadCommand = new CommandDetails(Collections.singletonList("reload"), "Reload files", "/TheCollections reload", "thecollections.reload", true, Duration.ZERO.getSeconds(), true, "thecollections");
    public CommandDetails mainMenuCommand = new CommandDetails(Collections.singletonList("mainmenu"), "Main Menu Command", "/TheCollections mainmenu", "thecollections.mainmenu", true, Duration.ZERO.getSeconds(), true, "thecollections");
    public CommandDetails collectionMenuCommand = new CommandDetails(Collections.singletonList("collection"), "Specific Collection Menu Command", "/TheCollections collection <id>", "thecollections.skillmenu", true, Duration.ZERO.getSeconds(), true, "thecollections");
    public CommandDetails categoryMenuCommand = new CommandDetails(Collections.singletonList("category"), "Specific Category Menu Command", "/TheCollections category <id>", "thecollections.skillmenu", true, Duration.ZERO.getSeconds(), true, "thecollections");
    public CommandDetails setLevelCommand = new CommandDetails(Collections.singletonList("setlevel"), "Set Collection level", "/TheCollections setlevel <player> <id> <amount>", "thecollections.setlevel", true, Duration.ZERO.getSeconds(), true, "thecollections");
    public CommandDetails setXPCommand = new CommandDetails(Collections.singletonList("setxp"), "Set Collection xp", "/TheCollections setxp <player> <id> <amount>", "thecollections.setxp", true, Duration.ZERO.getSeconds(), true, "thecollections");
    public CommandDetails helpCommand = new CommandDetails(Collections.singletonList("help"), "Show all commands", "/TheCollections help", "thecollections.help", true, Duration.ZERO.getSeconds(), true, "thecollections");
    public CommandDetails setItemCommand = new CommandDetails(Collections.singletonList("setitem"), "Set an item to collection", "/TheCollections setitem <id>", "thecollections.setitem", true, Duration.ZERO.getSeconds(), true, "thecollections");
    public CommandDetails dropItem = new CommandDetails(Collections.singletonList("dropitem"), "Drop an item from collection", "/TheCollections dropitem <id>", "thecollections.dropitem", true, Duration.ZERO.getSeconds(), true, "thecollections");

}
