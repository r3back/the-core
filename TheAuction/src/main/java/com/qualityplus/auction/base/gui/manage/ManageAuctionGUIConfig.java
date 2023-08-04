package com.qualityplus.auction.base.gui.manage;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Utility class for manage auction gui
 */
@Getter
public final class ManageAuctionGUIConfig extends OkaeriConfig implements SimpleGUI {
    private CommonGUI commonGUI;
    private Item auctionItem;
    private Item auctionItemSold;
    private Item goBackItem;
    private Item sortItem;
    private Item createAnAuction;
    private Item previousPageItem;
    private Item nextPageItem;
    private List<Integer> auctionSlots;


    /**
     * Makes manage auction gui config
     *
     * @param commonGUI        {@link CommonGUI}
     * @param auctionItemSold  {@link ManageAuctionGUIConfig}
     * @param auctionItem      {@link ManageAuctionGUIConfig}
     * @param goBackItem       {@link ManageAuctionGUIConfig}
     * @param sortItem         {@link ManageAuctionGUIConfig}
     * @param createAnAuction  {@link ManageAuctionGUIConfig}
     * @param previousPageItem {@link ManageAuctionGUIConfig}
     * @param nextPageItem     {@link ManageAuctionGUIConfig}
     * @param auctionSlots     {@link ManageAuctionGUIConfig}
     */
    @Builder
    public ManageAuctionGUIConfig(final CommonGUI commonGUI,
                                  final Item auctionItemSold,
                                  final Item auctionItem,
                                  final Item goBackItem,
                                  final Item sortItem,
                                  final Item createAnAuction,
                                  final Item previousPageItem,
                                  final Item nextPageItem,
                                  final List<Integer> auctionSlots) {
        this.commonGUI = commonGUI;
        this.auctionItem = auctionItem;
        this.goBackItem = goBackItem;
        this.sortItem = sortItem;
        this.createAnAuction = createAnAuction;
        this.previousPageItem = previousPageItem;
        this.nextPageItem = nextPageItem;
        this.auctionSlots = auctionSlots;
        this.auctionItemSold = auctionItemSold;
    }
}
