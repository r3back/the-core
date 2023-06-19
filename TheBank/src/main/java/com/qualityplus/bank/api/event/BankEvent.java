package com.qualityplus.bank.api.event;

import com.qualityplus.assistant.api.event.AssistantEvent;
import com.qualityplus.bank.persistence.data.BankData;
import com.qualityplus.bank.persistence.data.BankTransaction;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class BankEvent extends AssistantEvent {
    private final BankTransaction bankTransaction;
    private final BankData bankData;

    public BankEvent(final @NotNull BankData bankData, final BankTransaction transaction) {
        this.bankTransaction = transaction;
        this.bankData = bankData;
    }
}
