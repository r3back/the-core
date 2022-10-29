package com.qualityplus.souls.base.gui.tia;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
public final class TiaGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final CommonGUI commonGUI;
    private final Item tiaItem;

    @Builder
    public TiaGUIConfig(CommonGUI commonGUI, Item tiaItem) {
        this.commonGUI = commonGUI;
        this.tiaItem = tiaItem;
    }
}
