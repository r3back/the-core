package com.qualityplus.souls.base.gui.editgui;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public final class SoulsEditGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final CommonGUI commonGUI;
    private final Item goBackItem;
    private final Item deleteItem;
    private final Item teleportItem;
    private final Item addCommandItem;
    private final Item addMessageItem;

    @Builder
    public SoulsEditGUIConfig(CommonGUI commonGUI, Item goBackItem, Item deleteItem, Item teleportItem, Item addCommandItem, Item addMessageItem) {
        this.commonGUI = commonGUI;
        this.goBackItem = goBackItem;
        this.deleteItem = deleteItem;
        this.teleportItem = teleportItem;
        this.addCommandItem = addCommandItem;
        this.addMessageItem = addMessageItem;
    }
}
