package com.qualityplus.auction.base.gui.view.bin;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;


public final class BinAuctionViewGUIConfig extends OkaeriConfig implements SimpleGUI {
    public Item collectAuctionEmptyItem;
    public Item collectItemAuctionItem;
    public @Getter CommonGUI commonGUI;
    public Item collectAuctionItem;
    public Item cancelAuctionItem;
    public Item ownBuyItNowItem;
    public Item buyItNowItem;
    public Item auctionItem;
    public Item goBack;

    @Builder
    public BinAuctionViewGUIConfig(CommonGUI commonGUI, Item auctionItem, Item buyItNowItem, Item ownBuyItNowItem, Item cancelAuctionItem, Item goBack, Item collectItemAuctionItem, Item collectAuctionEmptyItem,
                                   Item collectAuctionItem) {
        this.collectAuctionEmptyItem = collectAuctionEmptyItem;
        this.collectItemAuctionItem = collectItemAuctionItem;
        this.collectAuctionItem = collectAuctionItem;
        this.cancelAuctionItem = cancelAuctionItem;
        this.ownBuyItNowItem = ownBuyItNowItem;
        this.buyItNowItem = buyItNowItem;
        this.auctionItem = auctionItem;
        this.commonGUI = commonGUI;
        this.goBack = goBack;

    }
}
