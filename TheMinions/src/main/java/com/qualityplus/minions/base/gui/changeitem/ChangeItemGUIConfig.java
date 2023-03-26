package com.qualityplus.minions.base.gui.changeitem;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
public final class ChangeItemGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final CommonGUI commonGUI;
    private final Item redItem;
    private final Item yellowItem;
    private final Item greenItem;
    private final Item oldItem;
    private final Item newItem;
    private final Item confirmItem;

    @Builder
    public ChangeItemGUIConfig(CommonGUI commonGUI, Item redItem, Item yellowItem, Item greenItem, Item oldItem, Item newItem, Item confirmItem) {
        this.commonGUI = commonGUI;
        this.confirmItem = confirmItem;
        this.redItem = redItem;
        this.yellowItem = yellowItem;
        this.greenItem = greenItem;
        this.oldItem = oldItem;
        this.newItem = newItem;
    }
}
