package com.qualityplus.bank.api.service;

import com.qualityplus.assistant.api.service.DataManagementService;
import com.qualityplus.bank.persistence.data.BankData;
import com.qualityplus.bank.persistence.data.BankTransaction;
import org.bukkit.entity.Player;

public interface BankService extends DataManagementService<BankData> {
    void handleTransaction(Player player, BankTransaction transaction);
}
