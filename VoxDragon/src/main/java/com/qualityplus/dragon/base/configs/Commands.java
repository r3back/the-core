package com.qualityplus.dragon.base.configs;

import com.qualityplus.assistant.api.commands.details.CommandDetails;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Exclude;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;

import java.time.Duration;
import java.util.Collections;

@Configuration(path = "commands.yml")
@Header("================================")
@Header("       Commands      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Commands extends OkaeriConfig {
    public CommandDetails reloadCommand = new CommandDetails(Collections.singletonList("reload"), "Reload files", "/TheDragon reload", "thedragon.reload", true, Duration.ZERO.getSeconds(), true, "thedragon");
    public CommandDetails helpCommand = new CommandDetails(Collections.singletonList("help"), "Show all commands", "/TheDragon help", "thedragon.help", true, Duration.ZERO.getSeconds(), true, "thedragon");
    public CommandDetails addCrystalCommand = new CommandDetails(Collections.singletonList("addcrystal"), "Add a dragon crystal to arena", "/TheDragon addcrystal", "thedragon.addcrystal", true, Duration.ZERO.getSeconds(), true, "thedragon");
    public CommandDetails addGuardianCommand = new CommandDetails(Collections.singletonList("addguardian"), "Add a dragon guardian to arena", "/TheDragon addguardian", "thedragon.addguardian", true, Duration.ZERO.getSeconds(), true, "thedragon");
    public CommandDetails addGuardianSpawnCommand = new CommandDetails(Collections.singletonList("addguardianspawn"), "Add a guardian spawn to arena", "/TheDragon addguardianspawn", "thedragon.addguardianspawn", true, Duration.ZERO.getSeconds(), true, "thedragon");
    public CommandDetails getRemoverToolCommand = new CommandDetails(Collections.singletonList("getremovertool"), "Get Remover's tool", "/TheDragon getremovertool", "thedragon.getremovertool", true, Duration.ZERO.getSeconds(), true, "thedragon");
    public CommandDetails startGameCommand = new CommandDetails(Collections.singletonList("start"), "Start Dragon Event", "/TheDragon start", "thedragon.start", true, Duration.ZERO.getSeconds(), true, "thedragon");
    public CommandDetails setDragonSpawnCommand = new CommandDetails(Collections.singletonList("setdragonspawn"), "Set Dragon spawn during event", "/TheDragon setdragonspawn", "thedragon.setdragonspawn", true, Duration.ZERO.getSeconds(), true, "thedragon");
    public CommandDetails mainMenuCommand = new CommandDetails(Collections.singletonList("mainmenu"), "Open Dragon event main menu", "/TheDragon mainmenu", "thedragon.mainmenu", true, Duration.ZERO.getSeconds(), true, "thedragon");
    public CommandDetails guardiansMenuCommand = new CommandDetails(Collections.singletonList("guardiansmenu"), "Open Dragon event guardians menu", "/TheDragon guardiansmenu", "thedragon.guardiansmenu", true, Duration.ZERO.getSeconds(), true, "thedragon");

    public CommandDetails giveEnderKeyCommand = new CommandDetails(Collections.singletonList("giveenderkey"), "Give ender key command", "/TheDragon giveenderkey <player> <amount>", "thedragon.giveenderkey", true, Duration.ZERO.getSeconds(), true, "thedragon");
    public CommandDetails stopGameCommand = new CommandDetails(Collections.singletonList("stop"), "Stop Dragon Event", "/TheDragon stop", "thedragon.stop", true, Duration.ZERO.getSeconds(), true, "thedragon");
    public CommandDetails setupModeCommand = new CommandDetails(Collections.singletonList("setupmode"), "Enter or leave from setup mode", "/TheDragon setupmode", "thedragon.setupmode", true, Duration.ZERO.getSeconds(), true, "thedragon");
    public @Exclude CommandDetails testCommand = new CommandDetails(Collections.singletonList("test"), "Add a dragon crystal to arena", "/TheDragon test", "thedragon.test", true, Duration.ZERO.getSeconds(), true, "thedragon");

}
