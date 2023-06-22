package com.qualityplus.collections.base.config;

import com.qualityplus.assistant.util.message.SpecialMessage;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;

import java.util.Collections;

@Configuration(path = "messages.yml")
@Header("================================")
@Header("       Messages      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Messages extends OkaeriConfig {
    public PluginMessages pluginMessages = new PluginMessages();
    public CollectionsMessages collectionsMessages = new CollectionsMessages();
    public CollectionsPlaceholders collectionsPlaceholders = new CollectionsPlaceholders();

    public static class CollectionsPlaceholders extends OkaeriConfig {
        public String isMaxedOutPlaceholder = "&7Collection Maxed Out";
        public String unlockedPlaceholder = "&7Collection Unlocked";
    }

    public static class CollectionsMessages extends OkaeriConfig {
        public String invalidItem = "&cYou must have a valid item in your hand!";

        public String successfullyChangedItem = "&aSuccessfully set Item of &e%collection_id% &aCollection to &e%collection_item_type% &aItem!";
        public String successfullyDroppedItem = "&aSuccessfully dropped Item of &e%collection_id% &aCollection Item Name: &e%collection_item_type% &aItem!";

        public String invalidCollection = "&cInvalid Collection";
        public String invalidCategory = "&cInvalid category";
        public SpecialMessage unlockedMessage = new SpecialMessage(Collections.singletonList("   &6&lCOLLECTION UNLOCKED &e%collection_displayname%"), "/thecollections collection %collection_id%", "&eClick to view your %collection_displayname% Collection!");

        public String lockedMessage = "&cThis collection is locked yet!";
        public String collectionIsDisabled = "&cThat collection is disabled!";
        public String levelCantBeHigherThanMaxLevel = "&cLevel can't be higher than max collection level!";

        public String setLevel = "&aYou set level &2%amount% &ato &2%object% &afor player &2%player%&a!";
        public String setXP = "&aYou set &2%amount% &aXP to &2%object% Collection &afor player &2%player%&a!, new level is &2%new_level% &aand new xp is &2%new_xp%!";

    }

    public static class PluginMessages extends OkaeriConfig {

        public String successfullyReloaded = "&aPlugin has been reloaded successfully!";

        public String invalidPlayer = "&cInvalid Player!";

        public String useSyntax = "&cUsage: %usage%!";

        public String noPermission = "&eYou don't have permission to do that!";
        public String unknownCommand = "&cUnknown command!";
        public String mustBeAPlayer = "&cYou must be a player to do that!";
        public String invalidArguments = "&cInvalid Arguments!";
        public String invalidAmount = "&cInvalid Amount!";
        public String useHelp = "&cUse: /thecollections help";

        public String helpMessage = "&7%command% - &e%description%";
        public String helpHeader = "      &6&lTheCollections   ";
        public String helpfooter = "&e<< &6Page %page% of %maxpage% &e>>";
        public String previousPage = "<<";
        public String nextPage = ">>";
        public String helpPageHoverMessage = "Click to go to page %page%";
    }
}
