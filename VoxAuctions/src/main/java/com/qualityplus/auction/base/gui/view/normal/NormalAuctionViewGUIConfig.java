package com.qualityplus.auction.base.gui.view.normal;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

/**
 * Utility class for normal auction view config
 */
@Getter
public final class NormalAuctionViewGUIConfig extends OkaeriConfig implements SimpleGUI {
    private CommonGUI commonGUI;
    private Item auctionItem;
    private Item submitBid;
    private Item bidAmount;
    private Item bidHistoryEmpty;
    private Item bidHistoryFilled;
    private Item goBack;
    private Item collectAuctionItem;
    private Item collectAuctionEmptyItem;
    private Item collectItemAuctionItem;

    /**
     * Makes a auction view gui config
     *
     * @param commonGUI               {@link CommonGUI}
     * @param collectItemAuctionItem  {@link NormalAuctionViewGUIConfig}
     * @param collectAuctionEmptyItem {@link NormalAuctionViewGUIConfig}
     * @param auctionItem             {@link NormalAuctionViewGUIConfig}
     * @param bidItem                 {@link NormalAuctionViewGUIConfig}
     * @param bidAmount               {@link NormalAuctionViewGUIConfig}
     * @param bidHistoryEmpty         {@link NormalAuctionViewGUIConfig}
     * @param bidHistoryFilled        {@link NormalAuctionViewGUIConfig}
     * @param goBack                  {@link NormalAuctionViewGUIConfig}
     * @param collectAuctionItem      {@link NormalAuctionViewGUIConfig}
     */
    @Builder
    public NormalAuctionViewGUIConfig( final CommonGUI commonGUI,
                                       final Item collectItemAuctionItem,
                                       final Item collectAuctionEmptyItem,
                                       final Item auctionItem,
                                       final Item bidItem,
                                       final Item bidAmount,
                                       final Item bidHistoryEmpty,
                                       final Item bidHistoryFilled,
                                       final Item goBack,
                                       final Item collectAuctionItem) {
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
