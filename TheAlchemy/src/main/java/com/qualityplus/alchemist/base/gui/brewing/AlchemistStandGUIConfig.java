package com.qualityplus.alchemist.base.gui.brewing;

import com.qualityplus.alchemist.base.stand.StandEffects;
import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public final class AlchemistStandGUIConfig extends OkaeriConfig implements SimpleGUI {
    private final CommonGUI commonGUI;
    private final Item emptyItem;
    private final List<Integer> decorationSlots;
    private final List<Integer> inputSlots;
    private final Integer fuelSlot;
    private final StandEffects standEffects;

    @Builder
    public AlchemistStandGUIConfig(CommonGUI commonGUI, Item emptyItem, List<Integer> inputSlots, Integer fuelSlot, List<Integer> decorationSlots, StandEffects standEffects) {
        this.fuelSlot = fuelSlot;
        this.commonGUI = commonGUI;
        this.inputSlots = inputSlots;
        this.emptyItem = emptyItem;
        this.standEffects = standEffects;
        this.decorationSlots = decorationSlots;
    }
}
