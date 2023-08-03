package com.qualityplus.auction.base.gui.pending;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Utility class for auction pending
 */
@Getter
public final class PendingAuctionGUIConfig extends OkaeriConfig implements SimpleGUI {
    private CommonGUI commonGUI;
    private Item auctionItem;

    private Item goBackItem;
    private Item previousPageItem;
    private Item nextPageItem;
    private List<Integer> auctionSlots;

    /**
     * Adds pending auction
     *
     * @param commonGUI        {@link CommonGUI}
     * @param auctionItem      {@link PendingAuctionGUIConfig}
     * @param goBackItem       {@link PendingAuctionGUIConfig}
     * @param previousPageItem {@link PendingAuctionGUIConfig}
     * @param nextPageItem     {@link PendingAuctionGUIConfig}
     * @param auctionSlots     {@link PendingAuctionGUIConfig}
     */
    @Builder
    public PendingAuctionGUIConfig(final CommonGUI commonGUI,
                                    final Item auctionItem,
                                     final Item goBackItem,
                                        final Item previousPageItem,
                                         final Item nextPageItem,
                                            final List<Integer> auctionSlots) {
        this.commonGUI = commonGUI;
        this.auctionItem = auctionItem;
        this.goBackItem = goBackItem;
        this.previousPageItem = previousPageItem;
        this.nextPageItem = nextPageItem;
        this.auctionSlots = auctionSlots;
    }
}
