package com.qualityplus.auction.base.gui.main;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

/**
 * Utility class for main auction gui config
 */
@Getter
public final class MainAuctionGUIConfig extends OkaeriConfig implements SimpleGUI {
    private CommonGUI commonGUI;
    private Item auctionsBrowser;
    private Item createAnAuction;
    private Item manageAuctions;
    private Item auctionStats;
    private Item viewBidsEmpty;
    private Item viewBids;

    /**
     * Makes a main auction
     *
     * @param commonGUI       {@link CommonGUI}
     * @param viewBids        {@link MainAuctionGUIConfig}
     * @param auctionsBrowser {@link MainAuctionGUIConfig}
     * @param createAnAuction {@link MainAuctionGUIConfig}
     * @param manageAuctions  {@link MainAuctionGUIConfig}
     * @param viewBidsEmpty   {@link MainAuctionGUIConfig}
     * @param auctionStats    {@link MainAuctionGUIConfig}
     */
    @Builder
    public MainAuctionGUIConfig(final CommonGUI commonGUI,
                                final Item viewBids,
                                final Item auctionsBrowser,
                                final Item createAnAuction,
                                final Item manageAuctions,
                                final Item viewBidsEmpty,
                                final Item auctionStats) {
        this.commonGUI = commonGUI;
        this.auctionsBrowser = auctionsBrowser;
        this.createAnAuction = createAnAuction;
        this.manageAuctions = manageAuctions;
        this.auctionStats = auctionStats;
        this.viewBidsEmpty = viewBidsEmpty;
        this.viewBids = viewBids;
    }
}
