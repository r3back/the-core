package com.qualityplus.bank.base.event;

import com.qualityplus.bank.api.event.BankEvent;
import com.qualityplus.bank.persistence.data.BankTransaction;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class BankInteractEvent extends BankEvent {
    public BankInteractEvent(@NotNull Player who, BankTransaction transaction) {
        super(who, transaction);
    }
}
