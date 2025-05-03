package com.qualityplus.alchemist.base.config;

import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;

import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

/**
 * Messages file representation
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
    private RecipeMessages recipeMessages = new RecipeMessages();
    private StandMessages standMessages = new StandMessages();

    /**
     * Messages related to brewing stand
     */
    @Getter
    @Setter
    public class StandMessages extends OkaeriConfig {
        private String alreadyInUse = "&cYou can't open that Brewing stand it's being used by %player%!";
    }

    /**
     * Messages related to recipes
     */
    @Getter
    @Setter
    public class RecipeMessages extends OkaeriConfig {
        private String recipeAlreadyExist = "&cThat Recipe already exists!";
        private String recipeDoesntExist = "&cThat Recipe doesn't exists!";
        private String defaultDescription = "&7Default Description...";
        private String successfullyDeletedRecipe = "&7That recipe has been successfully deleted!";
    }

    /**
     * Messages for plugin in general
     */
    @Getter
    @Setter
    public class PluginMessages extends OkaeriConfig {
        private String successfullyReloaded = "&aPlugin has been reloaded successfully!";
        private String invalidPlayer = "&cInvalid Player!";
        private String useSyntax = "&cUsage: %usage%!";
        private String noPermission = "&eYou don't have permission to do that!";
        private String unknownCommand = "&cUnknown command!";
        private String mustBeAPlayer = "&cYou must be a player to do that!";
        private String invalidArguments = "&cInvalid Arguments!";
        private String invalidAmount = "&cInvalid Amount!";
        private String useHelp = "&cUse: /alchemist help";

        private String helpMessage = "&7%command% - &e%description%";
        private String helpHeader = "      &6&lTheAlchemist   ";
        private String helpfooter = "&e<< &6Page %page% of %maxpage% &e>>";
        private String previousPage = "<<";
        private String nextPage = ">>";
        private String helpPageHoverMessage = "Click to go to page %page%";
    }
}
