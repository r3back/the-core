package com.qualityplus.auction.base.gui.time;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Utility class for time auction
 */
@Getter
public final class AuctionTimeConfigGUI extends OkaeriConfig implements SimpleGUI {
    private CommonGUI commonGUI;
    private Item timeItem;
    private Item goBack;
    private List<Integer> timeSlots;

    /**
     * Make auction time config
     *
     * @param commonGUI {@link CommonGUI}
     * @param timeItem  {@link AuctionTimeConfigGUI}
     * @param goBack    {@link AuctionTimeConfigGUI}
     * @param timeSlots {@link AuctionTimeConfigGUI}
     */
    @Builder
    public AuctionTimeConfigGUI(final CommonGUI commonGUI, final Item timeItem, final Item goBack, final List<Integer> timeSlots) {
        this.commonGUI = commonGUI;
        this.timeItem = timeItem;
        this.goBack = goBack;
        this.timeSlots = timeSlots;
    }
}
