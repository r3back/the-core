package com.qualityplus.auction.base.gui.stats;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

/**
 * Utility class for auction stats
 */
@Getter
public final class AuctionStatsGUIConfig extends OkaeriConfig implements SimpleGUI {
    private CommonGUI commonGUI;
    private Item sellerStatsItem;
    private Item buyerStatsItem;
    private Item goBackItem;

    /**
     * Makes auction stats
     *
     * @param commonGUI       {@link CommonGUI}
     * @param sellerStatsItem {@link AuctionStatsGUIConfig}
     * @param buyerStatsItem  {@link AuctionStatsGUIConfig}
     * @param goBackItem      {@link AuctionStatsGUIConfig}
     */
    @Builder
    public AuctionStatsGUIConfig(final CommonGUI commonGUI, final Item sellerStatsItem, final Item buyerStatsItem, final Item goBackItem) {
        this.commonGUI = commonGUI;
        this.sellerStatsItem = sellerStatsItem;
        this.buyerStatsItem = buyerStatsItem;
        this.goBackItem = goBackItem;
    }
}
