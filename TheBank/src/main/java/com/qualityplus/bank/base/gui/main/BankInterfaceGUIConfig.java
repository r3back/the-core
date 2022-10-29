package com.qualityplus.bank.base.gui.main;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
public final class BankInterfaceGUIConfig extends OkaeriConfig implements SimpleGUI {
    private final CommonGUI commonGUI;
    private final Item depositItem;
    private final Item withDrawItem;
    private final Item transactionsItem;
    private final Item informationItem;
    private final Item bankUpgradesItem;
    private final int transactionLimit;

    @Builder
    public BankInterfaceGUIConfig(CommonGUI commonGUI, Item depositItem, Item withDrawItem, Item transactionsItem, Item informationItem, Item bankUpgradesItem, int transactionLimit) {
        this.commonGUI = commonGUI;
        this.transactionLimit = transactionLimit;
        this.depositItem = depositItem;
        this.withDrawItem = withDrawItem;
        this.transactionsItem = transactionsItem;
        this.informationItem = informationItem;
        this.bankUpgradesItem = bankUpgradesItem;
    }
}
