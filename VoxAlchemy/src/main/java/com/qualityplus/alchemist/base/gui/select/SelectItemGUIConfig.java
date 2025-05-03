package com.qualityplus.alchemist.base.gui.select;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

/**
 * Select Item GUI Configuration
 */
@Getter
public final class SelectItemGUIConfig extends OkaeriConfig implements SimpleGUI {
    private final CommonGUI commonGUI;

    /**
     *
     * @param commonGUI {@link CommonGUI}
     */
    @Builder
    public SelectItemGUIConfig(final CommonGUI commonGUI) {
        this.commonGUI = commonGUI;
    }
}
