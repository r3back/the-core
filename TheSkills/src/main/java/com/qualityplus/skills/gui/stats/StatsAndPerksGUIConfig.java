package com.qualityplus.skills.gui.stats;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public final class StatsAndPerksGUIConfig extends OkaeriConfig implements SimpleGUI {
    private final @Getter CommonGUI commonGUI;
    public final Item previousPage;
    public final Item nextPage;
    public final Item goBack;
    public final Item statItem;
    public final Item perkItem;
    public final Item perkInfoItem;
    public final Item statInfoItem;
    public final Item switchMode;

    @Builder
    public StatsAndPerksGUIConfig(CommonGUI commonGUI, Item previousPage, Item nextPage, Item goBack, Item statItem, Item perkItem, Item perkInfoItem, Item statInfoItem, Item switchMode) {
        this.commonGUI = commonGUI;
        this.previousPage = previousPage;
        this.nextPage = nextPage;
        this.switchMode = switchMode;
        this.goBack = goBack;
        this.statItem = statItem;
        this.perkItem = perkItem;
        this.perkInfoItem = perkInfoItem;
        this.statInfoItem = statInfoItem;
    }
}
