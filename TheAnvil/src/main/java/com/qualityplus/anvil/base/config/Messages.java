package com.qualityplus.anvil.base.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
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
    public AnvilMessages anvilMessages = new AnvilMessages();
    public PluginMessages pluginMessages = new PluginMessages();
    public AnvilPlaceholders anvilPlaceholders = new AnvilPlaceholders();

    public class AnvilPlaceholders extends OkaeriConfig {
        public List<String> youCannotAddThatEnchantment = Arrays.asList("&7You cannot add that enchantment", "&7to that item!");
        public List<String> youCannotCombineThoseItems = Collections.singletonList("&7You cannot combine those items!");
        public String experienceCost = "&3%enchanting_enchant_exp_cost_amount% Exp Cost";
        public String moneyCost = "&6%enchanting_enchant_money_cost_amount% Coins";
    }

    public class AnvilMessages extends OkaeriConfig {
        public String thereIsAnItemToPickup = "&cThere is an item to pickup in the anvil!";
        public String youDontHaveEnoughMoney = "&cYou dont have enough money to enchant that item!";
        public String youDontHaveEnoughLevels = "&cYou dont have enough levels to enchant that item!";
        public String youDontHaveEnoughPermissions = "&cYou dont have enough permissions to enchant that item!";

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
        public String useHelp = "&cUse: /anvil help";

        public String helpMessage = "&7%command% - &e%description%";
        public String helpHeader = "      &6&lTheAnvil   ";
        public String helpfooter = "&e<< &6Page %page% of %maxpage% &e>>";
        public String previousPage = "<<";
        public String nextPage = ">>";
        public String helpPageHoverMessage = "Click to go to page %page%";
    }
}
