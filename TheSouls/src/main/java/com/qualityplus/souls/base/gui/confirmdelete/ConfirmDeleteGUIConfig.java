package com.qualityplus.souls.base.gui.confirmdelete;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
public final class ConfirmDeleteGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final CommonGUI commonGUI;
    private final Item confirmItem;
    private final Item cancelItem;
    private final Item goBackItem;

    @Builder
    public ConfirmDeleteGUIConfig(CommonGUI commonGUI, Item confirmItem, Item cancelItem, Item goBackItem) {
        this.commonGUI = commonGUI;
        this.confirmItem = confirmItem;
        this.cancelItem = cancelItem;
        this.goBackItem = goBackItem;
    }
}
