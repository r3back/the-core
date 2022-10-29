package com.qualityplus.alchemist.base.gui.individual;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

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

    @Builder
    public IndividualRecipeGUIConfig(CommonGUI commonGUI, Item durationItem, Item infoItem, Item fuelItem, Item outputItem, Item inputItem, XMaterial emptyItem, Item backPage) {
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
