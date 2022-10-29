package com.qualityplus.anvil.base.gui.anvilmain;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public final class AnvilMainGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final List<Integer> toUpgradeFilledSlots;
    private final Item toUpgradeFilledItem;
    private final int toUpgradeSlot;
    private final CommonGUI commonGUI;
    private final List<Integer> toSacrificeFilledSlots;
    private final Item toSacrificeFilledItem;
    private final int toSacrificeSlot;
    private final List<Integer> readyToCombineSlots;
    private final Item readyToCombineItem;
    private final Item combineFilledItem;

    private final Item combinedErrorItem;
    private final Item combinedFilledItem;

    @Builder
    public AnvilMainGUIConfig(List<Integer> itemToUpgradeFilledSlots, Item itemToUpgradeFilledItem, int itemToUpgradeSlot, CommonGUI commonGUI, List<Integer> itemToSacrificeFilledSlots,
                              Item itemToSacrificeFilledItem, int itemToSacrificeSlot, List<Integer> readyToCombineSlots, Item readyToCombineItem, Item combineFilledItem, Item combinedErrorItem,
                              Item combinedItemFilled) {
        this.toUpgradeFilledSlots = itemToUpgradeFilledSlots;
        this.toUpgradeFilledItem = itemToUpgradeFilledItem;
        this.toUpgradeSlot = itemToUpgradeSlot;
        this.commonGUI = commonGUI;
        this.toSacrificeFilledSlots = itemToSacrificeFilledSlots;
        this.toSacrificeFilledItem = itemToSacrificeFilledItem;
        this.toSacrificeSlot = itemToSacrificeSlot;
        this.readyToCombineSlots = readyToCombineSlots;
        this.readyToCombineItem = readyToCombineItem;
        this.combineFilledItem = combineFilledItem;
        this.combinedErrorItem = combinedErrorItem;
        this.combinedFilledItem = combinedItemFilled;
    }
}
