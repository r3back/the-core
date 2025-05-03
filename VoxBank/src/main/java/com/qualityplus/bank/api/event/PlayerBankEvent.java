package com.qualityplus.bank.api.event;

import com.qualityplus.assistant.api.event.PlayerAssistantEvent;
import com.qualityplus.bank.persistence.data.BankTransaction;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class PlayerBankEvent extends PlayerAssistantEvent {
    private @Getter final BankTransaction bankTransaction;

    public PlayerBankEvent(@NotNull Player who, BankTransaction transaction) {
        super(who);

        this.bankTransaction = transaction;
    }
}
