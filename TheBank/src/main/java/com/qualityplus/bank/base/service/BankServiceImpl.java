package com.qualityplus.bank.base.service;

import com.qualityplus.bank.api.service.BankService;
import com.qualityplus.bank.api.handler.TransactionHandler;
import com.qualityplus.bank.persistence.data.BankData;
import com.qualityplus.bank.persistence.data.BankTransaction;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public final class BankServiceImpl implements BankService {
    private final Map<UUID, BankData> dataMap = new HashMap<>();
    private @Inject TransactionHandler service;

    @Override
    public Optional<BankData> getBankData(UUID uuid) {
        return Optional.ofNullable(dataMap.getOrDefault(uuid, null));
    }

    @Override
    public void addData(BankData data) {
        dataMap.put(data.getUuid(), data);
    }

    @Override
    public void removeData(BankData data) {
        dataMap.remove(data.getUuid());
    }

    @Override
    public void handleTransaction(Player player, BankTransaction transaction) {
        service.handleTransaction(player, getBankData(player.getUniqueId()).orElse(new BankData()), transaction);
    }
}
