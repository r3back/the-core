package com.qualityplus.bank.base.handler;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.time.Markable;
import com.qualityplus.bank.api.box.Box;
import com.qualityplus.bank.api.response.TrxResponse;
import com.qualityplus.bank.api.transaction.TransactionType;
import com.qualityplus.bank.base.gui.main.BankInterfaceGUI;
import com.qualityplus.bank.persistence.data.BankData;
import com.qualityplus.bank.persistence.data.BankTransaction;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;

import eu.okaeri.platform.bukkit.annotation.Scheduled;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Scheduled(rate = MinecraftTimeEquivalent.SECOND, async = true)
public final class BankInterestHandler implements Runnable {
    private @Inject Box box;

    @Override
    public void run() {
        for(final Player player : Bukkit.getOnlinePlayers()){
            final BankData bankData = box.service().getData(player.getUniqueId()).orElse(new BankData());

            final Markable markable = new Markable(box.files().config().bankInterestDelay.getEffectiveTime(), bankData.getLastInterestTime());

            if (!markable.getRemainingTime().isZero()) {
                continue;
            }

            final double interest = bankData.getCalculatedInterest(box.files().bankUpgrades());

            if (interest <= 0) {
                continue;
            }

            final BankTransaction trx = new BankTransaction(interest, TransactionType.DEPOSIT, BankInterfaceGUI.GUIType.GENERAL);

            final Optional<TrxResponse> response = box.service().handleTransaction(player, trx, true);

            if (!response.isPresent()) {
                return;
            }

            bankData.setLastInterestTime(System.currentTimeMillis());
            bankData.setLastInterest(interest);

            final List<IPlaceholder> placeholders = new Placeholder("%bank_calculated_interest%", interest).alone();

            final String message = StringUtils.processMulti(box.files().messages().bankMessages.receivedInterestMessage, placeholders);

            player.sendMessage(message);
        }
    }
}