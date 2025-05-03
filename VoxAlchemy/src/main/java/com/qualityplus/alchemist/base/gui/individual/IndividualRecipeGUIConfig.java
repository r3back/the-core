package com.qualityplus.alchemist.base.gui.individual;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

/**
 * Individual Recipe GUI Configuration
 */
@Getter
public final class IndividualRecipeGUIConfig extends OkaeriConfig implements SimpleGUI {
    private final CommonGUI commonGUI;
    private final Item durationItem;
    private final Item infoItem;
    private final Item fuelItem;
    private final Item outputItem;
    private final Item inputItem;
    private final XMaterial emptyItem;
    private final Item backPage;

    /**
     *
     * @param commonGUI    {@link CommonGUI}
     * @param durationItem {@link Item}
     * @param infoItem     {@link Item}
     * @param fuelItem     {@link Item}
     * @param outputItem   {@link Item}
     * @param inputItem    {@link Item}
     * @param emptyItem    {@link XMaterial}
     * @param backPage     {@link Item}
     */
    @Builder
    public IndividualRecipeGUIConfig(final CommonGUI commonGUI, final Item durationItem, final Item infoItem, final Item fuelItem, final Item outputItem,
                                     final Item inputItem, final XMaterial emptyItem, final Item backPage) {
        this.commonGUI = commonGUI;
        this.durationItem = durationItem;
        this.infoItem = infoItem;
        this.fuelItem = fuelItem;
        this.outputItem = outputItem;
        this.inputItem = inputItem;
        this.emptyItem = emptyItem;
        this.backPage = backPage;
    }
}
