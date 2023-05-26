package com.qualityplus.bank.base.exception;

import com.qualityplus.bank.api.exception.BankException;
import com.qualityplus.bank.persistence.data.BankData;

public final class NotEnoughMoneyException extends BankException {
    public NotEnoughMoneyException(final BankData bankData) {
        super(bankData);
    }
}
