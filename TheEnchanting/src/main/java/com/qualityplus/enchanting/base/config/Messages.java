package com.qualityplus.enchanting.base.config;

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
    public PluginMessages pluginMessages = new PluginMessages();
    public EnchantingMessages enchantingMessages = new EnchantingMessages();
    public EnchantingPlaceholders enchantingPlaceholders = new EnchantingPlaceholders();

    public class EnchantingMessages extends OkaeriConfig {
        public String enchantApplied = "&aYou applied %enchanting_enchant_displayname% %enchanting_enchant_level_roman% to your %enchanting_item_name%!";
        public String enchantRemoved = "&cYou removed %enchanting_enchant_displayname% from your &a%enchanting_item_name%&c!";

        public String youDontHaveEnoughMoney = "&cYou dont have enough money to enchant that item!";
        public String youDontHaveEnoughLevels = "&cYou dont have enough levels to enchant that item!";
        public String youDontHaveEnoughPermissions = "&cYou dont have enough permissions to enchant that item!";
        public String higherLevelAlreadyPresent = "&cHigher level of enchantment already present!";

    }

    public class EnchantingPlaceholders extends OkaeriConfig{
        //enchanting_enchant_can_enchant
        public List<String> warningThisWillRemoveEnchantment = Arrays.asList("&c&lWARNING: This will remove %enchanting_enchant_conflict_displayname%!", "");
        public List<String> enchantmentIsAlreadyPresent = Arrays.asList("&cThis Enchantment is already present!", "");
        public List<String> emptyWarning = Collections.emptyList();


        //enchanting_enchant_click_placeholder
        public List<String> higherLevelPresent = Arrays.asList("&cHigher level already present!");
        public List<String> clickToRemove = Arrays.asList("&eClick to remove!");
        public List<String> clickToEnchant = Arrays.asList("&eClick to enchant!");
        public List<String> youDontHaveEnoughExpLevels = Arrays.asList("&cYou don't have enough exp levels!");
        public List<String> youDontHaveEnoughMoney = Arrays.asList("&cYou don't have enough money!");
        public List<String> youDontHaveEnoughPermissions = Arrays.asList("&cYou don't have enough permissions!");

        public String experienceCost = "&3%enchanting_enchant_exp_cost_amount% Exp Cost";
        public String moneyCost = "&6%enchanting_enchant_money_cost_amount% Coins";

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
        public String useHelp = "&cUse: /enchanting help";

        public String helpMessage = "&7%command% - &e%description%";
        public String helpHeader = "      &6&lTheEnchanting   ";
        public String helpfooter = "&e<< &6Page %page% of %maxpage% &e>>";
        public String previousPage = "<<";
        public String nextPage = ">>";
        public String helpPageHoverMessage = "Click to go to page %page%";

    }
}
