package com.qualityplus.alchemist.base.gui.select;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
public final class SelectItemGUIConfig extends OkaeriConfig implements SimpleGUI {
    private final CommonGUI commonGUI;

    @Builder
    public SelectItemGUIConfig(CommonGUI commonGUI) {
        this.commonGUI = commonGUI;
    }
}
