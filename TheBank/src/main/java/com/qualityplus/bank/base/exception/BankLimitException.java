package com.qualityplus.bank.base.exception;

import com.qualityplus.bank.api.exception.BankException;
import com.qualityplus.bank.persistence.data.BankData;

public final class BankLimitException extends BankException {
    public BankLimitException(BankData bankData) {
        super(bankData);
    }
}
