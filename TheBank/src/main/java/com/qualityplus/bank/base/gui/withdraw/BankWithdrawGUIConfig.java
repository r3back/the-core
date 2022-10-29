package com.qualityplus.bank.base.gui.withdraw;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
public final class BankWithdrawGUIConfig extends OkaeriConfig implements SimpleGUI {
    private final CommonGUI commonGUI;
    private final Item withdrawHalf;
    private final Item withdrawAll;
    private final Item withdrawAmount;
    private final Item withDrawCustomPercentage;

    private final Item goBack;
    private final double customPercentage;

    @Builder
    public BankWithdrawGUIConfig(CommonGUI commonGUI, Item withdrawHalf, Item withdrawAll, Item withdrawAmount, Item withDrawCustomPercentage, Item goBack, double customPercentage) {
        this.commonGUI = commonGUI;
        this.withdrawHalf = withdrawHalf;
        this.withdrawAll = withdrawAll;
        this.goBack = goBack;
        this.withdrawAmount = withdrawAmount;
        this.customPercentage = customPercentage;
        this.withDrawCustomPercentage = withDrawCustomPercentage;
    }
}
