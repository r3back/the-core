package com.qualityplus.bank.base.handler;

import com.qualityplus.bank.api.box.Box;
import com.qualityplus.bank.persistence.data.BankData;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.time.Markable;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Scheduled;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Scheduled(rate = MinecraftTimeEquivalent.SECOND, async = true)
public final class BankInterestHandler implements Runnable {
    private @Inject Box box;

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()){
            BankData bankData = box.service().getBankData(player.getUniqueId()).orElse(new BankData());

            Markable markable = new Markable(box.files().config().bankInterestDelay.getEffectiveTime(), bankData.getLastInterestTime());

            if(markable.getRemainingTime().isZero()){
                double interest = bankData.getCalculatedInterest(box.files().bankUpgrades());

                if(interest <= 0) continue;

                bankData.setLastInterestTime(System.currentTimeMillis());
                bankData.setLastInterest(interest);
                player.sendMessage(StringUtils.color(box.files().messages().bankMessages.receivedInterestMessage.replace("%bank_calculated_interest%", String.valueOf(interest))));
            }

        }
    }
}