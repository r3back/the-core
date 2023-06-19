package com.qualityplus.bank.base.event;

import com.qualityplus.bank.api.event.BankEvent;
import com.qualityplus.bank.persistence.data.BankData;
import com.qualityplus.bank.persistence.data.BankTransaction;
import org.jetbrains.annotations.NotNull;

public final class BankInteractEvent extends BankEvent {
    public BankInteractEvent(final @NotNull BankData who, final BankTransaction transaction) {
        super(who, transaction);
    }
}
