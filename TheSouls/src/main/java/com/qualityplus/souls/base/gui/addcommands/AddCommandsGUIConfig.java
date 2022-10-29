package com.qualityplus.souls.base.gui.addcommands;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public final class AddCommandsGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final List<Integer> commandsSlots;
    private final Item previousPageItem;
    private final CommonGUI commonGUI;
    private final Item addCommandItem;
    private final Item nextPageItem;
    private final Item commandItem;
    private final Item goBackItem;

    @Builder
    public AddCommandsGUIConfig(List<Integer> commandsSlots, Item previousPageItem, CommonGUI commonGUI, Item addCommandItem, Item nextPageItem, Item commandItem, Item goBackItem) {
        this.commandsSlots = commandsSlots;
        this.previousPageItem = previousPageItem;
        this.commonGUI = commonGUI;
        this.addCommandItem = addCommandItem;
        this.nextPageItem = nextPageItem;
        this.commandItem = commandItem;
        this.goBackItem = goBackItem;
    }
}
