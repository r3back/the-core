package com.qualityplus.runes.base.gui.options;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public final class AllRunesGUIConfig extends OkaeriConfig implements SimpleGUI {
    private final Item runeItem;
    private final Item nextPage;
    private final Item previousPage;
    private final CommonGUI commonGUI;
    private final List<Integer> runeSlots;

    @Builder
    public AllRunesGUIConfig(Item runeItem, List<Integer> runeSlots, Item previousPage, Item nextPage, CommonGUI commonGUI) {
        this.runeItem = runeItem;
        this.runeSlots = runeSlots;
        this.previousPage = previousPage;
        this.nextPage = nextPage;
        this.commonGUI = commonGUI;
    }
}
