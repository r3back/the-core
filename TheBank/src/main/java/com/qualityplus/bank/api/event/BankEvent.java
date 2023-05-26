package com.qualityplus.bank.api.event;

import com.qualityplus.assistant.api.event.HelperEvent;
import com.qualityplus.assistant.api.event.PlayerHelperEvent;
import com.qualityplus.bank.persistence.data.BankData;
import com.qualityplus.bank.persistence.data.BankTransaction;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class BankEvent extends HelperEvent {
    private final BankTransaction bankTransaction;
    private final BankData bankData;

    public BankEvent(final @NotNull BankData bankData, final BankTransaction transaction) {
        this.bankTransaction = transaction;
        this.bankData = bankData;
    }
}
