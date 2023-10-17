package com.qualityplus.skills.base.config;

import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

/**
 * Utility class for  messages
 */
@Getter
@Setter
@Configuration(path = "messages.yml")
@Header("================================")
@Header("       Messages      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Messages extends OkaeriConfig {
    private PluginMessages pluginMessages = new PluginMessages();
    private SkillsMessages skillsMessages = new SkillsMessages();

    /**
     * Utility class for  skills messages
     */
    @Getter
    public class SkillsMessages extends OkaeriConfig {
        private String invalidObject = "&cInvalid Object";
        private String statMode = "&aStat Mode";
        private String perkMode = "&aPerk Mode";

        private String skillsIsDisabled = "&cThat skill is disabled!";

        private String addedAmount = "&aYou added &2%amount% &alevels of &2%object% &ato &2%player%&a!, new level is &2%new_level%";
        private String removedAmount = "&aYou removed &2%amount% &alevels of &2%object% &ato &2%player%&a!, new level is &2%new_level%";
        private String successfullyResetStatsAndSkills = "&aYou successfully have reset stats for &2%player%&a!";

    }

    /**
     * Utility class for plugin messages
     */
    @Getter
    public class PluginMessages extends OkaeriConfig {


        private String successfullyReloaded = "&aPlugin has been reloaded successfully!";

        private String invalidPlayer = "&cInvalid Player!";

        private String useSyntax = "&cUsage: %usage%!";

        private String noPermission = "&eYou don't have permission to do that!";
        private String unknownCommand = "&cUnknown command!";
        private String mustBeAPlayer = "&cYou must be a player to do that!";
        private String invalidArguments = "&cInvalid Arguments!";
        private String invalidAmount = "&cInvalid Amount!";
        private String useHelp = "&cUse: /skills help";

        private String helpMessage = "&7%command% - &e%description%";
        private String helpHeader = "      &6&lTheSkills   ";
        private String helpfooter = "&e<< &6Page %page% of %maxpage% &e>>";
        private String previousPage = "<<";
        private String nextPage = ">>";
        private String helpPageHoverMessage = "Click to go to page %page%";

        private String successfullyAddedMoney = "&7Successfully added &a%amount% &7coins to &e%player% &7new balance is &a%player_balance%&7!";
        private String successfullyRemovedMoney = "&7Successfully added &c%amount% &7coins to &e%player% &7new balance is &c%player_balance%&7!";
        private String successfullySetMoney = "&7Successfully set coins of &e%player% &7to &c%player_balance%&7!";
        private String balanceCommand = "&7Your money is &c%player_balance%&7!";
        private String moneyCommand = "&7%player%'s money is &c%player_balance%&7!";

    }
}
