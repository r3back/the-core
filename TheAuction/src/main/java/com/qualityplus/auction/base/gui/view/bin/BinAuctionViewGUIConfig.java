package com.qualityplus.auction.base.gui.view.bin;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

/**
 * Utility class for bin auction gui config
 */
@Getter
public final class BinAuctionViewGUIConfig extends OkaeriConfig implements SimpleGUI {
    private final Item collectItemAuctionItem;
    private Item collectAuctionEmptyItem;
    private CommonGUI commonGUI;
    private Item collectAuctionItem;
    private Item cancelAuctionItem;
    private Item ownBuyItNowItem;
    private Item buyItNowItem;
    private Item auctionItem;
    private Item goBack;

    /**
     * Makes a bin auction view gui config
     *
     * @param commonGUI               {@link CommonGUI}
     * @param auctionItem             {@link BinAuctionViewGUIConfig}
     * @param buyItNowItem            {@link BinAuctionViewGUIConfig}
     * @param ownBuyItNowItem         {@link BinAuctionViewGUIConfig}
     * @param cancelAuctionItem       {@link BinAuctionViewGUIConfig}
     * @param goBack                  {@link BinAuctionViewGUIConfig}
     * @param collectItemAuctionItem  {@link BinAuctionViewGUIConfig}
     * @param collectAuctionEmptyItem {@link BinAuctionViewGUIConfig}
     * @param collectAuctionItem      {@link BinAuctionViewGUIConfig}
     */
    @Builder
    public BinAuctionViewGUIConfig(final CommonGUI commonGUI, final Item auctionItem, final Item buyItNowItem, final Item ownBuyItNowItem,
                                   final Item cancelAuctionItem, final Item goBack,
                                   final Item collectItemAuctionItem,
                                   final Item collectAuctionEmptyItem,
                                   final Item collectAuctionItem) {
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
