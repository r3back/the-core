package com.qualityplus.bank.base.config;

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
    public RecipeMessages recipeMessages = new RecipeMessages();
    public BankMessages bankMessages = new BankMessages();

    public class RecipeMessages extends OkaeriConfig{
        public String recipeAlreadyExist = "&cThat Recipe already exists!";
        public String recipeDoesntExist = "&cThat Recipe doesn't exists!";
        public String defaultDescription = "&7Default Description...";
        public String successfullyDeletedRecipe = "&7That recipe has been successfully deleted!";
    }

    public class BankMessages extends OkaeriConfig{
        public String successfullySetBankMoney = "&7You successfully set &e%player%'s bank money &7to &a%money%!";
        public String youCantDepositReachBankLimit = "&cYou can't deposit that amount, it reaches your bank limit!";
        public String youDontHaveAnyCoins = "&cYou don't have any coins!";
        public String youDontHaveAnyCoinsInYourBank = "&cYou don't have any coins in your Bank Account!";
        public String youDontHaveThatAmountToWithdraw = "&cYou don't have that amount of coins to withdraw!";

        public String youHaveDeposited = "&aYou have deposited &6%deposited% coins&a! You now have &6%bank_balance% coins &ain your account!";
        public String youHaveWithdrawn = "&aYou have withdrawn &6%withdrawn% coins&a! You now have &6%bank_balance% coins &ain your account!";

        public String receivedInterestMessage = "&aYou have just received &6%bank_calculated_interest% coins &aas bank interest!";

        public String personalBankRemainingTime = "&cYou can only open the personal bank every 1 hour!\n&cReaming cooldown: %hours_placeholder% %minutes_placeholder% %seconds_placeholder%";
        public String interestRemainingTime = "%days_placeholder% %hours_placeholder% %minutes_placeholder% %seconds_placeholder%";
        public String transactionDepositAgoTime = "&a+ &6%transaction_amount%&7, &e%days_placeholder% %hours_placeholder% %minutes_placeholder% %seconds_placeholder% ago";
        public String transactionWithdrawAgoTime = "&c- &6%transaction_amount%&7, &e%days_placeholder% %hours_placeholder% %minutes_placeholder% %seconds_placeholder% ago";

        public String days = "%days%d";
        public String hours = "%hours%h";
        public String minutes = "%minutes%m";
        public String seconds = "%seconds%s";
        public String noTime = "-";
        public boolean showTimeIfZero = true;

        public List<String> enterAmountToWithdraw = Arrays.asList("", "^^^^^^^^^^^^^^", "Enter amount", "to withdraw");
        public List<String> enterAmountToDeposit = Arrays.asList("", "^^^^^^^^^^^^^^", "Enter amount", "to deposit");

        public String youHaveBetterAccount = "&cYou have a better account!";
        public String thisIsYourAccount = "&aThis is your account!";
        public String notEnoughCoins = "&cNot Enough Coins!";
        public String notEnoughItems = "&cNot Enough Items!";
        public String needPreviousUpgrade = "&cNeed previous upgrade!";
        public String clickToBuy = "&eClick to buy!";

        public String successfullyUpdatedBank = "&aYou successfully updated your bank to %bank_upgrade_displayname%!";

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
        public String useHelp = "&cUse: /bank help";

        public String helpMessage = "&7%command% - &e%description%";
        public String helpHeader = "      &6&lTheBank   ";
        public String helpfooter = "&e<< &6Page %page% of %maxpage% &e>>";
        public String previousPage = "<<";
        public String nextPage = ">>";
        public String helpPageHoverMessage = "Click to go to page %page%";
    }


}
