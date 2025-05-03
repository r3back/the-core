package com.qualityplus.minions.base.gui.layout;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
public final class LayoutGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final CommonGUI commonGUI;
    private final Item minionItem;
    private final Item goBack;

    @Builder
    public LayoutGUIConfig(CommonGUI commonGUI, Item minionItem, Item goBack) {
        this.commonGUI = commonGUI;
        this.minionItem = minionItem;
        this.goBack = goBack;
    }
}
