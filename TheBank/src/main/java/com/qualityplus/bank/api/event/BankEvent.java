package com.qualityplus.bank.api.event;

import com.qualityplus.bank.persistence.data.BankTransaction;
import com.qualityplus.assistant.api.event.PlayerHelperEvent;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class BankEvent extends PlayerHelperEvent {
    private @Getter final BankTransaction bankTransaction;

    public BankEvent(@NotNull Player who, BankTransaction transaction) {
        super(who);

        this.bankTransaction = transaction;
    }
}
