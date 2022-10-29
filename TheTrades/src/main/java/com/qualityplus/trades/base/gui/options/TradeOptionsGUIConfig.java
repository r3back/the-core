package com.qualityplus.trades.base.gui.options;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public final class TradeOptionsGUIConfig extends OkaeriConfig implements SimpleGUI {
    private final Item goBack;
    private final CommonGUI commonGUI;
    private final Map<Integer, Item> itemsAndAmountToTrade;

    @Builder
    public TradeOptionsGUIConfig(CommonGUI commonGUI, Map<Integer, Item> itemsAndAmountToTrade, Item goBack) {
        this.goBack = goBack;
        this.commonGUI = commonGUI;
        this. itemsAndAmountToTrade = itemsAndAmountToTrade;
    }
}
