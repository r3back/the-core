package com.qualityplus.anvil.base.config

import eu.okaeri.configs.OkaeriConfig
import eu.okaeri.configs.annotation.Header
import eu.okaeri.configs.annotation.NameModifier
import eu.okaeri.configs.annotation.NameStrategy
import eu.okaeri.configs.annotation.Names
import eu.okaeri.platform.core.annotation.Configuration
import java.util.*

@Configuration(path = "messages.yml")
@Header("================================")
@Header("       Messages      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
class Messages : OkaeriConfig() {
    var anvilMessages = AnvilMessages()
    var pluginMessages: PluginMessages = PluginMessages()
    var anvilPlaceholders = AnvilPlaceholders()

    inner class AnvilPlaceholders : OkaeriConfig() {
        var youCannotAddThatEnchantment = Arrays.asList("&7You cannot add that enchantment", "&7to that item!")
        var youCannotCombineThoseItems = listOf("&7You cannot combine those items!")
        var experienceCost = "&3%enchanting_enchant_exp_cost_amount% Exp Cost"
        var moneyCost = "&6%enchanting_enchant_money_cost_amount% Coins"
    }

    inner class AnvilMessages : OkaeriConfig() {
        var thereIsAnItemToPickup = "&cThere is an item to pickup in the anvil!"
        var youDontHaveEnoughMoney = "&cYou dont have enough money to enchant that item!"
        var youDontHaveEnoughLevels = "&cYou dont have enough levels to enchant that item!"
        var youDontHaveEnoughPermissions = "&cYou dont have enough permissions to enchant that item!"
    }

    inner class PluginMessages : OkaeriConfig() {
        var successfullyReloaded = "&aPlugin has been reloaded successfully!"
        var invalidPlayer = "&cInvalid Player!"
        var useSyntax = "&cUsage: %usage%!"
        var noPermission = "&ecYou don't have permission to do that!"
        var unknownCommand = "&cUnknown command!"
        var mustBeAPlayer = "&cYou must be a player to do that!"
        var invalidArguments = "&cInvalid Arguments!"
        var invalidAmount = "&cInvalid Amount!"
        var useHelp = "&cUse: /anvil help"
        var helpMessage = "&7%command% - &e%description%"
        var helpHeader = "      &6&lTheAnvil   "
        var helpfooter = "&e<< &6Page %page% of %maxpage% &e>>"
        var previousPage = "<<"
        var nextPage = ">>"
        var helpPageHoverMessage = "Click to go to page %page%"
    }
}