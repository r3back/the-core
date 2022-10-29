package com.qualityplus.auction.base.gui.main;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public final class MainAuctionGUIConfig extends OkaeriConfig implements SimpleGUI {
    public @Getter CommonGUI commonGUI;
    public Item auctionsBrowser;
    public Item createAnAuction;
    public Item manageAuctions;
    public Item auctionStats;
    public Item viewBidsEmpty;
    public Item viewBids;

    @Builder
    public MainAuctionGUIConfig(CommonGUI commonGUI, Item viewBids, Item auctionsBrowser, Item createAnAuction, Item manageAuctions, Item viewBidsEmpty, Item auctionStats) {
        this.commonGUI = commonGUI;
        this.auctionsBrowser = auctionsBrowser;
        this.createAnAuction = createAnAuction;
        this.manageAuctions = manageAuctions;
        this.auctionStats = auctionStats;
        this.viewBidsEmpty = viewBidsEmpty;
        this.viewBids = viewBids;
    }
}
