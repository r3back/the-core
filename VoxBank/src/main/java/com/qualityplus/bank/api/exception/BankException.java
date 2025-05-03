package com.qualityplus.bank.api.exception;

import com.qualityplus.bank.persistence.data.BankData;
import lombok.Getter;

@Getter
public abstract class BankException extends RuntimeException {
    private final BankData bankData;

    public BankException(final BankData bankData) {
        this.bankData = bankData;
    }
}
