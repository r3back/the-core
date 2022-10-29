package com.qualityplus.bank.base.gui.upgrade;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
public final class BankUpgradeGUIConfig extends OkaeriConfig implements SimpleGUI {
    private final CommonGUI commonGUI;
    private final Item notCurrentUpgradeItem;
    private final Item currentUpgradeItem;
    private final Item goBackItem;

    @Builder
    public BankUpgradeGUIConfig(CommonGUI commonGUI, Item notCurrentUpgradeItem, Item currentUpgradeItem, Item goBackItem) {
        this.commonGUI = commonGUI;
        this.goBackItem = goBackItem;
        this.currentUpgradeItem = currentUpgradeItem;
        this.notCurrentUpgradeItem = notCurrentUpgradeItem;
    }
}
