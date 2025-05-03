package com.qualityplus.trades.base.gui.trades;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
public final class TradesGUIConfig extends OkaeriConfig implements SimpleGUI {
    private final CommonGUI commonGUI;
    private final Item goBack;
    private final Item nextPage;
    private final Item lockedItem;
    private final Item previousPage;
    private final Item unlockedItem;
    private final Item customGoBackItem;
    private final boolean showIconInLockedItem;
    private final boolean showIconInUnlockedItem;

    @Builder
    public TradesGUIConfig(CommonGUI commonGUI, Item goBack, Item nextPage, Item lockedItem, Item previousPage, Item unlockedItem, boolean showIconInLockedItem, boolean showIconInUnlockedItem,
                           Item customGoBackItem) {
        this.commonGUI = commonGUI;
        this.goBack = goBack;
        this.nextPage = nextPage;
        this.lockedItem = lockedItem;
        this.previousPage = previousPage;
        this.unlockedItem = unlockedItem;
        this.customGoBackItem = customGoBackItem;
        this.showIconInLockedItem = showIconInLockedItem;
        this.showIconInUnlockedItem = showIconInUnlockedItem;
    }
}
