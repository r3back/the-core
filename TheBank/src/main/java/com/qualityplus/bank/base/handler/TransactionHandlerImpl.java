package com.qualityplus.bank.base.handler;

import com.qualityplus.bank.api.handler.TransactionHandler;
import com.qualityplus.bank.base.config.BankUpgrades;
import com.qualityplus.bank.base.config.Messages;
import com.qualityplus.bank.base.event.BankInteractEvent;
import com.qualityplus.bank.base.gui.main.BankInterfaceGUI;
import com.qualityplus.bank.persistence.data.BankData;
import com.qualityplus.bank.persistence.data.BankTransaction;
import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.addons.EconomyAddon;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

@Component
public final class TransactionHandlerImpl implements TransactionHandler {
    private @Inject BankUpgrades upgrades;
    private @Inject Messages messages;

    @Override
    public void handleTransaction(Player player, @Nullable BankData bankData, BankTransaction transaction) {
        if(bankData == null) return;

        BankInteractEvent bankInteractEvent = new BankInteractEvent(player, transaction);

        Bukkit.getPluginManager().callEvent(bankInteractEvent);

        if(bankInteractEvent.isCancelled())
            return;

        switch (transaction.getType()){
            case SET:
                bankData.setMoney(transaction.getAmount());
                break;
            case DEPOSIT:
                handleDeposit(player, bankData, transaction);
                break;
            case WITHDRAW:
                handleWithDraw(player, bankData, transaction);
                break;
            default:
                break;
        }
    }

    private void handleDeposit(Player player, @NotNull BankData bankData, BankTransaction transaction){
        double newBalance = transaction.getAmount() + bankData.getMoney();

        if(newBalance > bankData.getLimit(upgrades)){
            player.sendMessage(StringUtils.color(messages.bankMessages.youCantDepositReachBankLimit));
            return;
        }

        EconomyAddon economy = TheAssistantPlugin.getAPI().getAddons().getEconomy();

        double balance = economy.getMoney(player);

        if(balance <= 0){
            player.sendMessage(StringUtils.color(messages.bankMessages.youDontHaveAnyCoins));
            return;
        }

        //transaction.setAmount(Math.max(transaction.getAmount(), balance));

        economy.withdrawMoney(player, transaction.getAmount());

        bankData.addMoney(transaction.getAmount());

        List<IPlaceholder> placeholder = Arrays.asList(new Placeholder("deposited", transaction.getAmount()), new Placeholder("bank_balance", bankData.getMoney()));

        player.sendMessage(StringUtils.processMulti(messages.bankMessages.youHaveDeposited, placeholder));

        bankData.addTrx(transaction);

        if(transaction.getGuiType() == BankInterfaceGUI.GUIType.GENERAL) return;

        bankData.setLastTimeUsedPersonal(System.currentTimeMillis());
    }

    private void handleWithDraw(Player player, @NotNull BankData bankData, BankTransaction transaction){
        EconomyAddon economy = TheAssistantPlugin.getAPI().getAddons().getEconomy();

        double bankMoney = bankData.getMoney();

        if(bankMoney <= 0){
            player.sendMessage(StringUtils.color(messages.bankMessages.youDontHaveAnyCoinsInYourBank));
            return;
        }

        if(transaction.getAmount() > bankMoney){
            player.sendMessage(StringUtils.color(messages.bankMessages.youDontHaveThatAmountToWithdraw));
            return;
        }

        transaction.setAmount(Math.max(transaction.getAmount(), 0));

        economy.depositMoney(player, transaction.getAmount());

        bankData.removeMoney(transaction.getAmount());

        List<IPlaceholder> placeholder = Arrays.asList(new Placeholder("withdrawn", transaction.getAmount()), new Placeholder("bank_balance", bankData.getMoney()));

        player.sendMessage(StringUtils.processMulti(messages.bankMessages.youHaveWithdrawn, placeholder));

        bankData.addTrx(transaction);

        if(transaction.getGuiType() == BankInterfaceGUI.GUIType.GENERAL) return;

        bankData.setLastTimeUsedPersonal(System.currentTimeMillis());

    }
}
