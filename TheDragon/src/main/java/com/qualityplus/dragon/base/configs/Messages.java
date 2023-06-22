package com.qualityplus.dragon.base.configs;

import com.qualityplus.assistant.api.config.ConfigTitle;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration(path = "messages.yml")
@Header("================================")
@Header("       Messages      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Messages extends OkaeriConfig {

    public SetupMessages setupMessages = new SetupMessages();
    public GameConfigMessages gameConfigMessages = new GameConfigMessages();
    public GameTitlesMessages gameTitlesMessages = new GameTitlesMessages();
    public PluginMessages pluginMessages = new PluginMessages();
    public GameMessages gameMessages = new GameMessages();

    public class PluginMessages extends OkaeriConfig {
        public String successfullyReloaded = "&aPlugin has been reloaded successfully!";
        public String invalidPlayer = "&cInvalid Player!";
        public String useSyntax = "&cUsage: %usage%!";
        public String noPermission = "&eYou don't have permission to do that!";
        public String unknownCommand = "&cUnknown command!";
        public String mustBeAPlayer = "&cYou must be a player to do that!";
        public String invalidArguments = "&cInvalid Arguments!";
        public String invalidAmount = "&cInvalid Amount!";
        public String useHelp = "&cUse: /thedragon help";
        public String helpMessage = "&7%command% - &e%description%";
        public String helpHeader = "      &6&lTheDragon   ";
        public String helpfooter = "&e<< &6Page %page% of %maxpage% &e>>";
        public String previousPage = "<<";
        public String nextPage = ">>";
        public String helpPageHoverMessage = "Click to go to page %page%";
    }

    public static class GameMessages extends OkaeriConfig{
        public String thatBlockIsNotAnAltar = "%prefix% &cThat block is not an altar!";
        public String gameCantStartSpawn = "%prefix% &cGame Can't start, Dragon Spawn is not set!";
        public String gameCantStartStructure = "%prefix% &cGame Can't start, Dragon Structure is not set!";
        public String cantPlaceSpawn = "%prefix% &cGame Can't place that, Dragon Spawn is not set!";
        public String cantPlaceStructure = "%prefix% &cGame Can't place that, Dragon Structure is not set!";

        public String alreadyPlacedAltar = "%prefix% &cThere is a ender key placed in this altar!";
        public String gameEventBossBar = "%thedragon_dragon_displayname% - &a%thedragon_dragon_health%&c/%thedragon_dragon_max_health% &c| Next Event in %thedragon_next_event_remaining_time%";
        public String placedEnderKey = "%prefix% &aSuccessfully placed ender eye &d%thedragon_ender_key_current%&5/&d%thedragon_ender_key_total%&a!";
        public String altarsFilled = "%prefix% &dAltars has been filled, Starting Dragon Event!";
        public String newRecordPlaceholder = "&d&l(NEW RECORD) ";
        public String forbiddenPlayer = "none";
    }

    public static class SetupMessages extends OkaeriConfig{
        public String guardianAdded = "%prefix% &7Successfully added dragon guardian &5%thedragon_guardian_id%&7!";
        public String guardianAlreadyExist = "%prefix% &7There is a guardian with that id!";
        public String spawnSet = "%prefix% &7Spawn was successfully set in &5%thedragon_spawn_location%&7!";
        public String crystalSet = "%prefix% &7Dragon Crystal was successfully added in &5%thedragon_crystal_location%&7!";
        public String guardianSpawnSet = "%prefix% &7Guardian spawn was successfully added in &5%thedragon_guardian_spawn_location%&7!";
        public String setupModeLeft = "%prefix% &aYou have left from setup mode!";
        public String altarSet = "%prefix% &7Dragon Altar was successfully added in &5%location%&7!";
        public String altarRemoved = "%prefix% &7Altar was successfully removed!";
        public String thereIsntAnAltarToRemove = "%prefix% &7There isn't an altar to remove there!";
        public String thereIsAnAltarPlaced = "%prefix% &7There is an altar already placed there!";
        public String errorInSetupMode = "%prefix% &7You can't do that in setup mode!";
        public String errorDragonEventInProgress = "%prefix% &cYou can't do that! Dragon Game event is currently active!";
        public String guardianEditModeMessage = "&6► &6Type in the chat to set the new &6%type%&e! Type &cstop &eto exit from setup mode.";
        public String guardianEquipItemDoesntMatch = "&6► &cItem type doesn't match!";
        public String guardianMobTypeDoesntMatch = "&6► &cMob type doesn't match!";

        public List<String> altarSetupMode = Arrays.asList(
                "&5&m▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
                ,"&dYou've joined in altar setup mode!"
                ," &5► &dRight-Click in a block to remove altar."
                ," &5► &dLeft-Click in a block to set it as an Altar."
                ,"&dType command again to leave from altar setup mode."
                ,"&5&m▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

        public List<String> newGameEndMessage = Arrays.asList(
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
                ,""
                ,"                &a%the_dragon_last_attacker% &7He hit the dragon the last blow."
                ,""
                ,"                &e&l1st Damager &7- %the_dragon_player_top_1_name% &7- &e%the_dragon_player_top_1_damage%"
                ,"                &6&l2nd Damager &7- %the_dragon_player_top_2_name% &7- &e%the_dragon_player_top_2_damage%"
                ,"                &c&l3rd Damager &7- %the_dragon_player_top_3_name% &7- &e%the_dragon_player_top_3_damage%"
                ,"                "
                ,"                &eYour Damage: &a%the_dragon_player_damage% %the_dragon_player_got_new_record%&7(Position #%the_dragon_player_rank%)"
                ,"                &eRuneCrafting Experience: &d%the_dragon_player_reward_xp%"
                ,"                "
                ,"&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
    }

    public static class GameConfigMessages extends OkaeriConfig{
        public boolean enabled = true;
        public String lightning = "%prefix% &aLightning Event has started, run away!";
        public String guardians = "%prefix% &aGuardians Event has started, prepare your swords!";
        public String fireball = "%prefix% &aFireball Event has started, run away!";
        public String dragonBall = "%prefix% &aDragon balls Event has started, run away!";
    }

    public static class GameTitlesMessages extends OkaeriConfig{
        public ConfigTitle dragonSpawning = new ConfigTitle("&5Dragon Spawning", "&6Spawning %thedragon_dragon_spawning_time%");
        public ConfigTitle dragonSpawned = new ConfigTitle("&c%thedragon_dragon_displayname%", "&7Spawned");
    }
}
