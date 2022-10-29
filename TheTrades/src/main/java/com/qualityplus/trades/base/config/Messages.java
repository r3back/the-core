package com.qualityplus.trades.base.config;

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
    public TradeMessages tradeMessages = new TradeMessages();

    public class TradeMessages extends OkaeriConfig{
        public String recipeAlreadyExist = "&cThat Recipe already exists!";
        public String recipeDoesntExist = "&cThat Recipe doesn't exists!";
        public String defaultDescription = "&7Default Description...";
        public String successfullyDeletedRecipe = "&7That recipe has been successfully deleted!";

        public String noPermission = "&cYou haven't unlocked that trade yet!";

        public String noMoney = "&cYou don't have the required money for this trade!";
        public String noItems = "&cYou don't have the required items for this trade!";
        public String youBought = "&aYou Bought &f%trade_result_item_name% &8x%trade_amount%&a!";

        public List<String> costFormat = Arrays.asList("&7Cost", "%trade_cost_list%");

        public String moneyCostFormat = "&6%trade_money_price% coins";

        public String itemCostFormat = "&f%trade_cost_item_name% &8x%trade_cost_item_amount%";

        public String itemResultAmountFormat = "&8x%amount%";
        public String itemResultAmountOneFormat = "";

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
        public String useHelp = "&cUse: /trades help";

        public String helpMessage = "&7%command% - &e%description%";
        public String helpHeader = "      &6&lTheTrades   ";
        public String helpfooter = "&e<< &6Page %page% of %maxpage% &e>>";
        public String previousPage = "<<";
        public String nextPage = ">>";
        public String helpPageHoverMessage = "Click to go to page %page%";
    }
}
