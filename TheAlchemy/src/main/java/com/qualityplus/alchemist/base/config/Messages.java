package com.qualityplus.alchemist.base.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

@Configuration(path = "messages.yml")
@Header("================================")
@Header("       Messages      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Messages extends OkaeriConfig {
    public PluginMessages pluginMessages = new PluginMessages();
    public RecipeMessages recipeMessages = new RecipeMessages();

    public class RecipeMessages extends OkaeriConfig{
        public String recipeAlreadyExist = "&cThat Recipe already exists!";
        public String recipeDoesntExist = "&cThat Recipe doesn't exists!";
        public String defaultDescription = "&7Default Description...";
        public String successfullyDeletedRecipe = "&7That recipe has been successfully deleted!";
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
        public String useHelp = "&cUse: /alchemist help";

        public String helpMessage = "&7%command% - &e%description%";
        public String helpHeader = "      &6&lTheAlchemist   ";
        public String helpfooter = "&e<< &6Page %page% of %maxpage% &e>>";
        public String previousPage = "<<";
        public String nextPage = ">>";
        public String helpPageHoverMessage = "Click to go to page %page%";
    }
}
