package com.qualityplus.bank.persistence.data;

import com.qualityplus.bank.base.gui.main.BankInterfaceGUI.GUIType;
import com.qualityplus.bank.api.transaction.TransactionType;
import eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = true)
public final class BankTransaction extends Document {
    private double amount;
    private long whenHappened;
    private TransactionType type;
    private GUIType guiType;

    public BankTransaction(double amount, TransactionType type, GUIType guiType) {
        this.whenHappened = System.currentTimeMillis();
        this.guiType = guiType;
        this.amount = amount;
        this.type = type;
    }
}
