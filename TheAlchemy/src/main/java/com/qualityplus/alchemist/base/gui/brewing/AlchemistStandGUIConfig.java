package com.qualityplus.alchemist.base.gui.brewing;

import com.qualityplus.alchemist.base.stand.StandEffects;
import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Alchemist Stand GUI Configuration
 */
@Getter
public final class AlchemistStandGUIConfig extends OkaeriConfig implements SimpleGUI {
    private final CommonGUI commonGUI;
    private final Item emptyItem;
    private final List<Integer> decorationSlots;
    private final List<Integer> inputSlots;
    private final Integer fuelSlot;
    private final StandEffects standEffects;

    /**
     *
     * @param commonGUI       {@link CommonGUI}
     * @param emptyItem       {@link Item}
     * @param inputSlots      List of {@link Integer}
     * @param fuelSlot        Fuel slot
     * @param decorationSlots List of {@link Integer}
     * @param standEffects    {@link StandEffects}
     */
    @Builder
    public AlchemistStandGUIConfig(final CommonGUI commonGUI, final Item emptyItem, final List<Integer> inputSlots, final Integer fuelSlot,
                                   final List<Integer> decorationSlots, final StandEffects standEffects) {
        this.fuelSlot = fuelSlot;
        this.commonGUI = commonGUI;
        this.inputSlots = inputSlots;
        this.emptyItem = emptyItem;
        this.standEffects = standEffects;
        this.decorationSlots = decorationSlots;
    }
}
