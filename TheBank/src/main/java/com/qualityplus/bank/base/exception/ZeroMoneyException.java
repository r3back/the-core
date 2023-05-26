package com.qualityplus.bank.base.exception;

import com.qualityplus.bank.api.exception.BankException;
import com.qualityplus.bank.persistence.data.BankData;

/**
 * Exception thrown when player has zero money in his bank
 */
public final class ZeroMoneyException extends BankException {
    public ZeroMoneyException(final BankData bankData) {
        super(bankData);
    }
}
