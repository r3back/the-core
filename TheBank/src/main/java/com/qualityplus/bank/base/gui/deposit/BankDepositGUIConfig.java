package com.qualityplus.bank.base.gui.deposit;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
public final class BankDepositGUIConfig extends OkaeriConfig implements SimpleGUI {
    private final CommonGUI commonGUI;
    private final Item depositHalf;
    private final Item depositAll;
    private final Item depositCustomAmount;
    private final Item goBack;

    @Builder
    public BankDepositGUIConfig(CommonGUI commonGUI, Item depositHalf, Item depositAll, Item depositCustomAmount, Item goBack) {
        this.commonGUI = commonGUI;
        this.depositHalf = depositHalf;
        this.depositAll = depositAll;
        this.goBack = goBack;
        this.depositCustomAmount = depositCustomAmount;
    }
}
