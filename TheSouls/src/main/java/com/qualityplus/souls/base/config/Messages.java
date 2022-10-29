package com.qualityplus.souls.base.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration(path = "messages.yml")
@Header("================================")
@Header("       Messages      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Messages extends OkaeriConfig {
    public PluginMessages pluginMessages = new PluginMessages();
    public SoulsMessages soulsMessages = new SoulsMessages();

    public class SoulsMessages extends OkaeriConfig {
        public String soulAlreadyFound = "&dYou have already found that Fairy Soul!";
        public String soulFound = "&d&lSOUL! &fYou found a &dFairy Soul&f!";
        public String firstSoulsFound = "&d&lSOUL! &aArchivement Unlocked: &d&lFirst Fairy Soul Found&f!";
        public String allSoulsFound = "&d&lSOUL! &fCongratulations you found &dAll Souls&f!";


        public String soulAlreadyPlaced = "&d&lSOULS &f» &cYou already placed a soul there!";
        public String soulPlaced = "&d&lSOULS &f» &7You successfully placed a soul &dTotal: &5%souls_total%&7!";
        public String soulRemoved = "&d&lSOULS &f» &7You successfully removed that soul &dTotal: &5%souls_total%&7!";

        public String successfullyAddedMessage = "&aSuccessfully added &7%soul_message% &ato the soul messages!";
        public String successfullyAddedCommand = "&aSuccessfully added &7%soul_command% &ato the soul commands!";

        public String typeACommand = "&7Type the &acommand &7in the chat to add to the soul commands or type &ccancel &7to exit from edit mode!";
        public String typeAMessage = "&7Type the &amessage &7in the chat to add to the soul messages or type &ccancel &7to exit from edit mode!";


        public String clickToClaim = "&eClick to claim!";
        public String notEnoughSoulsPlaceholder = "&cYou don't have enough Fairy Souls!";
        public String notEnoughSoulsMessage = "&cYou don't have enough Fairy Souls!";

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
        public String useHelp = "&cUse: /souls help";

        public String helpMessage = "&7%command% - &e%description%";
        public String helpHeader = "      &6&lTheSouls   ";
        public String helpfooter = "&e<< &6Page %page% of %maxpage% &e>>";
        public String previousPage = "<<";
        public String nextPage = ">>";
        public String helpPageHoverMessage = "Click to go to page %page%";
    }


}
