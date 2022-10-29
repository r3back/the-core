package com.qualityplus.bank.api.handler;

import com.qualityplus.bank.persistence.data.BankData;
import com.qualityplus.bank.persistence.data.BankTransaction;
import org.bukkit.entity.Player;

public interface TransactionHandler {
    void handleTransaction(Player player, BankData bankData, BankTransaction transaction);
}
