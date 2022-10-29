package com.qualityplus.runes.base.gui.derune;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.runes.base.table.effects.RuneTableEffects;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public final class RemoveRuneGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final List<Integer> toUpgradeFilledSlots;
    private final Item toUpgradeFilledItem;
    private final int toUpgradeSlot;
    private final CommonGUI commonGUI;
    private final Item clickToRemoveRune;
    private final Item combinedErrorItem;
    private final Item successItem;
    private final Item toRemoveItem;
    private final Item goBack;

    @Builder
    public RemoveRuneGUIConfig(Item goBack, List<Integer> itemToUpgradeFilledSlots, Item toRemoveItem, Item itemToUpgradeFilledItem, int itemToUpgradeSlot, CommonGUI commonGUI, Item clickToRemoveRune, Item combinedErrorItem, Item successItem) {
        this.toUpgradeFilledSlots = itemToUpgradeFilledSlots;
        this.toUpgradeFilledItem = itemToUpgradeFilledItem;
        this.toUpgradeSlot = itemToUpgradeSlot;
        this.successItem = successItem;
        this.commonGUI = commonGUI;
        this.clickToRemoveRune = clickToRemoveRune;
        this.combinedErrorItem = combinedErrorItem;
        this.toRemoveItem = toRemoveItem;
        this.goBack = goBack;
    }
}
