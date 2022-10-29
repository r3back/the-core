package com.qualityplus.runes.base.gui.runetable;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.runes.base.table.effects.RuneTableEffects;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public final class RuneTableGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final List<Integer> toUpgradeFilledSlots;
    private final Item toUpgradeFilledItem;
    private final int toUpgradeSlot;
    private final CommonGUI commonGUI;
    private final List<Integer> toSacrificeFilledSlots;
    private final Item toSacrificeFilledItem;
    private final int toSacrificeSlot;
    private final List<Integer> readyToCombineSlots;
    private final Item readyToCombineItem;
    private final Item clickToCombineRunesItem;
    private final Item clickToCombineItemAndRuneItem;
    private final Item combinedErrorItem;
    private final Item combinedFilledItem;

    private final Item removalItem;

    private final RuneTableEffects runeTableEffects;

    @Builder
    public RuneTableGUIConfig(List<Integer> itemToUpgradeFilledSlots, Item itemToUpgradeFilledItem, int itemToUpgradeSlot, CommonGUI commonGUI, List<Integer> itemToSacrificeFilledSlots,
                              Item itemToSacrificeFilledItem, int itemToSacrificeSlot, List<Integer> readyToCombineSlots, Item readyToCombineItem, Item clickToCombineRunesItem, Item combinedErrorItem,
                              Item combinedItemFilled, Item clickToCombineItemAndRuneItem, RuneTableEffects runeTableEffects, Item removalItem) {
        this.toUpgradeFilledSlots = itemToUpgradeFilledSlots;
        this.toUpgradeFilledItem = itemToUpgradeFilledItem;
        this.toUpgradeSlot = itemToUpgradeSlot;
        this.removalItem = removalItem;
        this.commonGUI = commonGUI;
        this.toSacrificeFilledSlots = itemToSacrificeFilledSlots;
        this.toSacrificeFilledItem = itemToSacrificeFilledItem;
        this.toSacrificeSlot = itemToSacrificeSlot;
        this.readyToCombineSlots = readyToCombineSlots;
        this.readyToCombineItem = readyToCombineItem;
        this.clickToCombineRunesItem = clickToCombineRunesItem;
        this.combinedErrorItem = combinedErrorItem;
        this.combinedFilledItem = combinedItemFilled;
        this.clickToCombineItemAndRuneItem = clickToCombineItemAndRuneItem;
        this.runeTableEffects = runeTableEffects;
    }
}
