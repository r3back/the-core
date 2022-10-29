package com.qualityplus.bank.api.service;

import com.qualityplus.bank.persistence.data.BankData;
import com.qualityplus.bank.persistence.data.BankTransaction;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public interface BankService {
    Optional<BankData> getBankData(UUID uuid);

    void addData(BankData data);

    void removeData(BankData data);

    void handleTransaction(Player player, BankTransaction transaction);
}
