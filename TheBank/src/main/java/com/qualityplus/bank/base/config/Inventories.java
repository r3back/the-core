package com.qualityplus.bank.base.config;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.bank.base.gui.deposit.BankDepositGUIConfig;
import com.qualityplus.bank.base.gui.main.BankInterfaceGUIConfig;
import com.qualityplus.bank.base.gui.upgrade.BankUpgradeGUIConfig;
import com.qualityplus.bank.base.gui.withdraw.BankWithdrawGUIConfig;
import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.background.DefaultBackgrounds;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;

@Getter
@Setter
@Configuration(path = "inventories.yml")
@Header("================================")
@Header("       Inventories      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Inventories extends OkaeriConfig implements DefaultBackgrounds {
    @CustomKey("bankGUI")
    public BankInterfaceGUIConfig bankGUI = BankInterfaceGUIConfig.builder()
            .commonGUI(new CommonGUI(
                    "Personal Bank Account",
                    36,
                    getBackGroundFiller(),
                    ItemBuilder.of(XMaterial.BARRIER,  31, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).build()
            ))
            .transactionLimit(10)
           .depositItem(ItemBuilder.of(XMaterial.CHEST,  11, 1, "&aDeposit Coins", Arrays.asList(
                   "&7Current balance: &6%bank_balance%",
                   "",
                   "&7Store coins in the bank to keep",
                   "&7them safe while you go on",
                   "&7adventures!",
                   "",
                   "&7You will earn &b%bank_interest_percentage%% &7interest every",
                   "&7day for your bank balance.",
                   "",
                   "&7Until interest: &b%bank_interest_remaining_time%",
                   "",
                   "&eClick to make a deposit!")).build())
           .withDrawItem(ItemBuilder.of(XMaterial.FURNACE,  13, 1, "&aWithdraw Coins", Arrays.asList(
                   "&7Current balance: &6%bank_balance%",
                   "",
                   "&7Take your coins out of the bank",
                   "&7in order to spend them.",
                   "",
                   "&eClick to withdraw!")).build())
           .transactionsItem(ItemBuilder.of(XMaterial.FILLED_MAP,  15, 1, "&aRecent transactions", Arrays.asList(
                   "", "&7%bank_transaction_list%")).build())
           .informationItem(ItemBuilder.of(XMaterial.REDSTONE_TORCH,  32, 1, "&aInformation", Arrays.asList(
                   "&7Keep your coins safe in the bank!",
                   "",
                   "&7Balance limit: &6%bank_limit_amount%",
                   "",
                   "&7The banker rewards you every 24",
                   "&7hours with &binterest &7for the",
                   "&7coins in your bank balance.",
                   "",
                   "&7Interest in: &b%bank_interest_remaining_time%",
                   "&7Last interest: &6%bank_interest_last% coins"
                   )).build())
           .bankUpgradesItem(ItemBuilder.of(XMaterial.GOLD_BLOCK,  35, 1, "&6Bank Upgrades", Arrays.asList(
                   "&7Are you so rich that you can't",
                   "&7even store your coins?",
                   "",
                   "&7Current Account: &6%bank_limit_displayname%",
                   "&7Bank limit: &6%bank_limit_amount%",
                   "",
                   "&eClick to view upgrades!"
           )).build())
            .build();


    @CustomKey("bankDepositGUI")
    public BankDepositGUIConfig bankDepositGUIConfig = BankDepositGUIConfig.builder()
            .commonGUI(new CommonGUI(
                    "Bank Deposit",
                    36,
                    getBackGroundFiller(),
                    ItemBuilder.of(XMaterial.BARRIER,  31, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).enabled(false).build()
            ))
            .depositAll(ItemBuilder.of(XMaterial.CHEST,  11, 64, "&aYour whole purse", Arrays.asList(
                    "&8Bank deposit",
                    "",
                    "&7Current balance: &6%bank_balance%",
                    "&7Amount to deposit: &6%player_balance%",
                    "",
                    "&eClick to deposit coins!")).build())
            .depositHalf(ItemBuilder.of(XMaterial.CHEST,  13, 32, "&aHalf your purse", Arrays.asList(
                    "&8Bank deposit",
                    "",
                    "&7Current balance: &6%bank_balance%",
                    "&7Amount to deposit: &6%player_half_balance%",
                    "",
                    "&eClick to deposit coins!")).build())
            .depositCustomAmount(ItemBuilder.of(XMaterial.OAK_SIGN,  15, 1, "&aSpecific amount", Arrays.asList(
                    "&7Current balance: &6%bank_balance%",
                    "",
                    "&eClick to deposit coins!")).build())

            .goBack(ItemBuilder.of(XMaterial.ARROW,  31, 1, "&aGo Back", Collections.singletonList(
                    "&7To Personal Bank Account")).build())

            .build();

    @CustomKey("bankWithdrawGUI")
    public BankWithdrawGUIConfig bankWithdrawGUIConfig = BankWithdrawGUIConfig.builder()
            .commonGUI(new CommonGUI(
                    "Bank Withdraw",
                    36,
                    getBackGroundFiller(),
                    ItemBuilder.of(XMaterial.BARRIER,  31, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).enabled(false).build()
            ))
            .withdrawAll(ItemBuilder.of(XMaterial.DROPPER,  10, 64, "&aEverything in the account", Arrays.asList(
                    "&8Bank withdrawal",
                    "",
                    "&7Current balance: &6%bank_balance%",
                    "&7Amount to withdraw: &6%bank_balance%",
                    "",
                    "&eClick to withdraw coins!")).build())
            .withdrawHalf(ItemBuilder.of(XMaterial.DROPPER,  12, 32, "&aHalf the account", Arrays.asList(
                    "&8Bank withdrawal",
                    "",
                    "&7Current balance: &6%bank_balance%",
                    "&7Amount to withdraw: &6%bank_half_balance%",
                    "",
                    "&eClick to deposit coins!")).build())
            .withDrawCustomPercentage(ItemBuilder.of(XMaterial.DROPPER,  14, 1, "&aWithdraw 20%", Arrays.asList(
                    "&8Bank withdrawal",
                    "",
                    "&7Current balance: &6%bank_balance%",
                    "&7Amount to withdraw: &6%bank_balance_custom_percentage%",
                    "",
                    "&eClick to deposit coins!")).build())
            .withdrawAmount(ItemBuilder.of(XMaterial.OAK_SIGN,  16, 1, "&aSpecific amount", Arrays.asList(
                    "&7Current balance: &6%bank_balance%",
                    "",
                    "&eClick to withdraw coins!")).build())
            .customPercentage(20)
            .goBack(ItemBuilder.of(XMaterial.ARROW,  31, 1, "&aGo Back", Collections.singletonList(
                    "&7To Personal Bank Account")).build())

            .build();

    @CustomKey("bankUpgradeGUI")
    public BankUpgradeGUIConfig bankUpgradeGUI = BankUpgradeGUIConfig.builder()
            .commonGUI(new CommonGUI(
                    "Bank Account Upgrades",
                    36,
                    getBackGroundFiller(),
                    ItemBuilder.of(XMaterial.BARRIER,  31, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).enabled(false).build()
            ))
            .notCurrentUpgradeItem(ItemBuilder.of(XMaterial.STONE, 1, "%bank_upgrade_displayname%", Arrays.asList("%bank_upgrade_description%", "", "%bank_upgrade_status%")).build())
            .currentUpgradeItem(ItemBuilder.of(XMaterial.STONE, 1, "%bank_upgrade_displayname%", Arrays.asList("%bank_upgrade_description%", "", "%bank_upgrade_status%")).enchanted(true).build())
            .goBackItem(ItemBuilder.of(XMaterial.ARROW,  31, 1, "&aGo Back", Collections.singletonList("&7To Personal Bank Account")).build())
            .build();
}
