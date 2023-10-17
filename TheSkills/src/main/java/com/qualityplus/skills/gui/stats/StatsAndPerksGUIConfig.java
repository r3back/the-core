package com.qualityplus.skills.gui.stats;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

/**
 * Utility class for stats and perks gui config
 */
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

    /**
     * Makes a stats and perks gui config
     *
     * @param commonGUI    {@link CommonGUI}
     * @param previousPage {@link StatsAndPerksGUIConfig}
     * @param nextPage     {@link StatsAndPerksGUIConfig}
     * @param goBack       {@link StatsAndPerksGUIConfig}
     * @param statItem     {@link StatsAndPerksGUIConfig}
     * @param perkItem     {@link StatsAndPerksGUIConfig}
     * @param perkInfoItem {@link StatsAndPerksGUIConfig}
     * @param statInfoItem {@link StatsAndPerksGUIConfig}
     * @param switchMode   {@link StatsAndPerksGUIConfig}
     */
    @Builder
    public StatsAndPerksGUIConfig(final CommonGUI commonGUI,
                                  final Item previousPage,
                                  final Item nextPage,
                                  final Item goBack,
                                  final Item statItem,
                                  final Item perkItem,
                                  final Item perkInfoItem,
                                  final Item statInfoItem,
                                  final Item switchMode) {
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
