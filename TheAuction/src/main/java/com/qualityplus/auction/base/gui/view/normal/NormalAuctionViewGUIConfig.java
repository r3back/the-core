package com.qualityplus.auction.base.gui.view.normal;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

public final class NormalAuctionViewGUIConfig extends OkaeriConfig implements SimpleGUI {
    public @Getter CommonGUI commonGUI;
    public Item auctionItem;
    public Item submitBid;
    public Item bidAmount;
    public Item bidHistoryEmpty;
    public Item bidHistoryFilled;
    public Item goBack;
    public Item collectAuctionItem;
    public Item collectAuctionEmptyItem;
    public Item collectItemAuctionItem;

    @Builder
    public NormalAuctionViewGUIConfig(CommonGUI commonGUI, Item collectItemAuctionItem, Item collectAuctionEmptyItem, Item auctionItem, Item bidItem, Item bidAmount, Item bidHistoryEmpty, Item bidHistoryFilled, Item goBack, Item collectAuctionItem) {
        this.goBack = goBack;
        this.commonGUI = commonGUI;
        this.auctionItem = auctionItem;
        this.submitBid = bidItem;
        this.bidAmount = bidAmount;
        this.bidHistoryFilled = bidHistoryFilled;
        this.bidHistoryEmpty = bidHistoryEmpty;
        this.collectAuctionItem = collectAuctionItem;
        this.collectAuctionEmptyItem = collectAuctionEmptyItem;
        this.collectItemAuctionItem = collectItemAuctionItem;
    }
}
