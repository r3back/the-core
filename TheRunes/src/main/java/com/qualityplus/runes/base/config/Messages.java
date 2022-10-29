package com.qualityplus.runes.base.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration(path = "messages.yml")
@Header("================================")
@Header("       Messages      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Messages extends OkaeriConfig {
    public PluginMessages pluginMessages = new PluginMessages();

    public RuneMessages runeMessages = new RuneMessages();
    public RunePlaceholders runePlaceholders = new RunePlaceholders();

    public class RunePlaceholders extends OkaeriConfig {
        public List<String> youCannotCombineThoseItems = Collections.singletonList("&7You cannot combine those items!");

        public List<String> mustBeSameTier = Arrays.asList("&7Both runes must be of the same", "&7tier to combine!");

        public List<String> mustBeSameType = Arrays.asList("&7Both runes must be of the same", "&7type to combine!");

        public List<String> youCannotAddThatRuneToThatItem = Arrays.asList("&7You cannot add that rune to that", "&7item!");

        public List<String> noRuneCraftingLevel = Arrays.asList("&7Applying this rune requires a", "&7higher Runecrafting level!");

    }

    public class RuneMessages extends OkaeriConfig {
        public String thatRuneDoesntExists = "&cThat rune doesn't exist!";
        public String thereIsAnItemToPickup = "&cThere is an item to pickup in the rune table!";
        public String youDontHaveEnoughRunecraftingLevel = "&cYou dont have required runecrafting level!";
        public String youDidntSucceed = "&cYou didn't succeed fusing your runes together!";
        public String addedXpFusing = "&5+&d%rune_session_xp% &5Runecrafting XP &8- &5Fusing %rune_session_result_displayname%!";
        public String addedXpApplying = "&5+&d%rune_session_xp% &5Runecrafting XP &8- &5Applying %rune_to_sacrifice_displayname%!";
        public String removedRune = "&aSuccessfully removed rune!";
        public String thereIsAnItemPlaced = "&cThere is an item to pick up!";

    }

    public class PluginMessages extends OkaeriConfig {
        public String successfullyReloaded = "&aPlugin has been reloaded successfully!";
        public String invalidPlayer = "&cInvalid Player!";
        public String useSyntax = "&cUsage: %usage%!";

        public String noPermission = "&ecYou don't have permission to do that!";
        public String unknownCommand = "&cUnknown command!";
        public String mustBeAPlayer = "&cYou must be a player to do that!";
        public String invalidArguments = "&cInvalid Arguments!";
        public String invalidAmount = "&cInvalid Amount!";
        public String useHelp = "&cUse: /runes help";

        public String helpMessage = "&7%command% - &e%description%";
        public String helpHeader = "      &6&lTheRunes   ";
        public String helpfooter = "&e<< &6Page %page% of %maxpage% &e>>";
        public String previousPage = "<<";
        public String nextPage = ">>";
        public String helpPageHoverMessage = "Click to go to page %page%";
    }
}
